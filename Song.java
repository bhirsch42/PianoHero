import org.newdawn.slick.*;
import java.util.*;
public class Song {

	private In data;
	private Sound song;
	private long startTime;
	private boolean started = false;
	private ArrayList<Note> notes;
	private int score = 0;

	public Song(String songFilename, String dataFilename) {
		this.data = new In(dataFilename);
		try {
			this.song = new Sound(songFilename);
		}
		catch (SlickException e) {
			e.printStackTrace();
		}

		notes = new ArrayList<Note>();
		while (!data.isEmpty()) {
			String s[] = data.readLine().split(" ");
			notes.add(new Note(Double.parseDouble(s[0]), s[1]));
		}

	}

	public Song(String songFilename) {
		try {
			this.song = new Sound(songFilename);
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void start(GameContainer container) {
		if (started)
			return;
		started = true;
		song.play();
		this.startTime = container.getTime();
	}

	public double getTime(GameContainer container) {
		return container.getTime() - startTime;
	}

	public void update(GameContainer container, int delta) {
		// if (Note.aKeyPressed(container)) {
		// 	for (Note note : notes) {
		// 		if (!note.hasBeenHit() && note.canHit(container, getTime(container))) {
		// 			if (note.hit(container, this.getTime(container))) {
		// 				score++;
		// 			}
		// 		}
		// 	}
		// }
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

}