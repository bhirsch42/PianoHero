import org.newdawn.slick.*;
import java.awt.Toolkit;
import java.awt.Dimension;

public class SongEditor extends BasicGame {

	public SongEditor() {
		super("Song Editor");
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new SongEditor());
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

	public final double BPM = 170;
	public int beat = 0;

	public void advance(GameContainer container, int beats) {
		spikeInARail.start(container, beat, beat+beats, BPM);
		beat += beats;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		spikeInARail.updateEditor(container, delta);
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_K))
			spikeInARail.start(container, 32, 64, BPM);
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		spikeInARail.render(container, g);
	}

}