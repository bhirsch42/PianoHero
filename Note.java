import org.newdawn.slick.*;
import java.util.*;

public class Note {

	public double time;
	public String key;
	public boolean hit = false;;
	public float place;
	public static final float BUFFER = 100.0f;

	public Note(double time, String key) {
		this.time = time;
		this.key = key;
		this.place = initPlace();
	}

	public double getTime() {
		return time;
	}

	public String getKey() {
		return key;
	}


	public float initPlace() {
		if (this.key.equals("a"))
			return 0.0f;
		if (this.key.equals("s"))
			return 1.0f;
		if (this.key.equals("d"))
			return 2.0f;
		if (this.key.equals("f"))
			return 3.0f;
		if (this.key.equals("j"))
			return 4.0f;
		if (this.key.equals("k"))
			return 5.0f;
		if (this.key.equals("l"))
			return 6.0f;
		if (this.key.equals(";"))
		return 7.0f;
		return 8.0f;
	}

	public float getPlace() {
		return place;
	}

	public static String[] getPressedKeys(GameContainer container) {
		Input input = container.getInput();
		// have to call every key
		ArrayList<String> pressedKeys = new ArrayList<String>();

		if (input.isKeyPressed(Input.KEY_A)) {
			pressedKeys.add("a");
		}		
		if (input.isKeyPressed(Input.KEY_S)) {
			pressedKeys.add("s");
		}		
		if (input.isKeyPressed(Input.KEY_D)) {
			pressedKeys.add("d");
		}		
		if (input.isKeyPressed(Input.KEY_F)) {
			pressedKeys.add("f");
		}		
		if (input.isKeyPressed(Input.KEY_J)) {
			pressedKeys.add("j");
		}		
		if (input.isKeyPressed(Input.KEY_K)) {
			pressedKeys.add("k");
		}		
		if (input.isKeyPressed(Input.KEY_L)) {
			pressedKeys.add("l");
		}		
		if (input.isKeyPressed(Input.KEY_SEMICOLON)) {
			pressedKeys.add(";");
		}
		String[] keys = new String[pressedKeys.size()];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = pressedKeys.get(i);
		}
		return keys;
	}

	public boolean noteKeyPressed(GameContainer container) {
		Input input = container.getInput();
		if ((this.key.equals("a") && input.isKeyPressed(Input.KEY_A)) ||		
			(this.key.equals("s") && input.isKeyPressed(Input.KEY_S)) ||		
			(this.key.equals("d") && input.isKeyPressed(Input.KEY_D)) ||		
			(this.key.equals("f") && input.isKeyPressed(Input.KEY_F)) ||		
			(this.key.equals("j") && input.isKeyPressed(Input.KEY_J)) ||		
			(this.key.equals("k") && input.isKeyPressed(Input.KEY_K)) ||		
			(this.key.equals("l") && input.isKeyPressed(Input.KEY_L)) ||		
			(this.key.equals(";") && input.isKeyPressed(Input.KEY_SEMICOLON)))
		{
			return true;
		}
		return false;
	}

	public boolean canHit(GameContainer container, double time) {
		if (time-BUFFER < this.getTime() && this.getTime() < time + BUFFER) {
			return true;
		}
		return false;
	}

	public boolean hit(GameContainer container, double time) {
		if (hit)
			return true;
		if (canHit(container, time) && noteKeyPressed(container)) {
			hit = true;
			return true;
		}
		return false;
	}

	public void markHit() {
		hit = true;
	}

	public boolean hasBeenHit() {
		return hit;
	}

	public void render(GameContainer container, Graphics g, float h) {

	}
}