import org.newdawn.slick.*;
import java.util.*;
public class Song {

	private In data;
	private double[] song;
	private long startTime;
	private boolean started = false;
	private ArrayList<Note> notes;
	private int score = 0;

	private class ThreadPlayer implements Runnable {
		private double[] clip;
		
		public ThreadPlayer(double[] clip) {
			this.clip = clip;
		}

		public void run() {
			for (int i = 0; i < clip.length; i++) {
				StdAudio.play(clip[i]);
			}
			StdAudio.close();
		} 

	}

	public Song(String songFilename, String dataFilename) {
		this.data = new In(dataFilename);
		// try {
		// 	this.song = new Sound(songFilename);
		// }
		// catch (SlickException e) {
		// 	e.printStackTrace();
		// }
		double[] songRead = StdAudio.read(songFilename);
		song = new double[songRead.length/2];
		for (int i = 0; i < songRead.length; i+=2) {
			song[i/2] = songRead[i];
		}
		notes = new ArrayList<Note>();
		while (!data.isEmpty()) {
			String s[] = data.readLine().split(" ");
			notes.add(new Note(Double.parseDouble(s[0]), s[1]));
		}

	}

	public Song(String songFilename) {
		double[] songRead = StdAudio.read(songFilename);
		song = new double[songRead.length/2];
		for (int i = 0; i < songRead.length; i+=2) {
			song[i/2] = songRead[i];
		}
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public void threadPlay(double[] clip) {
		ThreadPlayer player = new ThreadPlayer(clip);
		Thread t = new Thread(player);
		t.start();
	}

	public void start(GameContainer container) {
		if (started)
			return;
		System.out.println(song.length);
		started = true;
		threadPlay(song);
		this.startTime = container.getTime();
	}

	public void start(double startBeat, double endBeat, double bpm) {
		startBeat--;
		// endBeat++;

		double rate = 1000.0 / bpm * 44100.0/64.0;
		int startIndex = (int)(startBeat * rate);
		int endIndex = (int)(endBeat * rate);

		if (startIndex < 0) startIndex = 0;
		if (endIndex < startIndex) endIndex = startIndex;
		if (endIndex >= song.length) endIndex = song.length-1;

		double[] clip = new double[endIndex-startIndex];
		for (int i = startIndex; i < endIndex; i++) {
			clip[i-startIndex] = song[i];
		}
		threadPlay(clip);
	}

	public double getTime(GameContainer container) {
		return container.getTime() - startTime;
	}

	public void update(GameContainer container, int delta) {
		String[] keys = Note.getPressedKeys(container);
		if (keys.length > 0) {
			for (Note note : notes) {
				for (String key : keys) {
					if (note.canHit(container, getTime(container)) && key.equals(note.getKey())) {
						note.markHit();
					}
				}
			}
		}
	}

	private float speed = 0.4f;

	private void drawNoteRect(GameContainer container, Graphics g, String key, float x, float y) {
		float width = 40.0f;
		float height = Note.BUFFER*speed;
		g.drawRect(x-width/2f, y-height/2f, width, height);
		// g.drawLine(x-width/2f, y, x+width/2f, y);
		g.drawString(key, x-5f, y-10f);
	}

	public void render(GameContainer container, Graphics g) {
		if (!started)
			return;
		float line = (float)container.getHeight() - 200.0f;
		float shift = (float)container.getWidth() / 8.0f / 2.0f;
		for (Note note : notes) {
			if (!note.hasBeenHit()) {
				float x = note.getPlace() * shift + 100.0f;
				float y = ((float)getTime(container) - (float)note.getTime())*speed + line;
				drawNoteRect(container, g, note.getKey(), x, y);
			}
		}
		g.drawString(this.getTime(container)+"", 100f, 100f);
		g.drawLine(0.0f, line, 2000f, line);
	}

	public static void main(String[] args) {
		int startBeat = Integer.parseInt(args[0]);
		int endBeat = Integer.parseInt(args[1]);
		double bpm = Double.parseDouble(args[2]);
		Song spike = new Song("SpikeInARail.wav");
		spike.start(startBeat, endBeat, bpm);
	}

}