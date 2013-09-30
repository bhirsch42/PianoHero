import java.util.*;

public class WorkspaceConverter {

	public static void main(String[] args) {
		String filename = args[0];
		double bpm = Double.parseDouble(args[1]);
		double offset = Double.parseDouble(args[2]);
		//187.3257142857143
		In in = new In(filename);
		Out out = new Out("converted_" + filename);
		// init times and keys
		ArrayList<String> keys = new ArrayList<String>();
		ArrayList<Integer> beats = new ArrayList<Integer>();
		while(!in.isEmpty()) {
			String s = in.readLine();
			String sp[] = s.split(" ");
			if (sp.length > 1) {
				for (int i = 1; i < sp.length; i++) {
					beats.add(Integer.parseInt(sp[0]));
					keys.add(sp[i]);
				}
			}
		}

		double beat = bpm/4.0;
		// output cleaned song
		for (int i = 0; i < keys.size(); i++) {
			out.print((double)beats.get(i)*beat + offset);
			out.println(" " + keys.get(i));
		}


	}

}