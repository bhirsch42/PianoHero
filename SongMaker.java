import org.newdawn.slick.*;
import java.awt.Toolkit;
import java.awt.Dimension;

public class SongMaker extends BasicGame {

	public SongMaker() {
		super("Song Maker");
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new SongMaker());
			app.setShowFPS(true);
			// set fullscreen of native resolution
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int)screenSize.getWidth();
			int height = (int)screenSize.getHeight();
			app.setDisplayMode(width, height, true);
			// app.setDisplayMode(1366, 768, false);
			app.setTargetFrameRate(120);
			app.start();
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public Song song;
	public Out out;

	@Override
	public void init(GameContainer container) throws SlickException {
		out = new Out("SpikeInARail.txt");
		song = new Song("SpikeInARail.wav");
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();

		song.start(container);
		if (input.isKeyPressed(Input.KEY_A)) {
			out.print(song.getTime(container));
			out.println(" a");
		}
		if (input.isKeyPressed(Input.KEY_S)) {
			out.print(song.getTime(container));
			out.println(" s");
		}
		if (input.isKeyPressed(Input.KEY_D)) {
			out.print(song.getTime(container));
			out.println(" d");
		}
		if (input.isKeyPressed(Input.KEY_F)) {
			out.print(song.getTime(container));
			out.println(" f");
		}
		if (input.isKeyPressed(Input.KEY_J)) {
			out.print(song.getTime(container));
			out.println(" j");
		}
		if (input.isKeyPressed(Input.KEY_K)) {
			out.print(song.getTime(container));
			out.println(" k");
		}
		if (input.isKeyPressed(Input.KEY_L)) {
			out.print(song.getTime(container));
			out.println(" l");
		}
		if (input.isKeyPressed(Input.KEY_SEMICOLON)) {
			out.print(song.getTime(container));
			out.println(" ;");
		}


	}

	public void render(GameContainer container, Graphics g) throws SlickException {


	}

}