import org.newdawn.slick.*;
import java.awt.Toolkit;
import java.awt.Dimension;

public class SongEditor extends BasicGame {

	public PianoHero() {
		super("Song Editor");
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new PianoHero());
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

	public Song spikeInARail;

	@Override
	public void init(GameContainer container) throws SlickException {
		spikeInARail = new Song("SpikeInARail.wav", "converted_workspace_SpikeInARail.txt");
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		spikeInARail.start(container);

		spikeInARail.update(container, delta);
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		spikeInARail.render(container, g);
	}

}