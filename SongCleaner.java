public class SongCleaner {

	public static void main(String[] args) {
		String filename = args[0];
		double bpm = Double.parseDouble(args[1]);
		//187.3257142857143
		In in = new In(filename);
		Out out = new Out("clean_" + filename);

		// find number of notes
		int numLines = 0;
		while (!in.isEmpty()) {
			in.readLine();
			numLines++;
		}
		in = new In(filename);

		// init times and keys
		double[] times = new double[numLines];
		String[] keys = new String[numLines];
		for (int i = 0; i < numLines; i++) {
			String s = in.readLine();
			String sp[] = s.split(" ");
			times[i] = Double.parseDouble(sp[0]);
			keys[i] = sp[1];
		}

		// refine beat
		double beat = bpm/4.0;
		for (int i = 0; i < times.length; i++) {
			double time = times[i];
			double lower = Math.floor(time / beat) * beat;
			double upper = lower + beat;
			double newTime = upper-time < time-lower ? upper : lower;
			times[i] = newTime;
		}

		// output cleaned song
		for (int i = 0; i < numLines; i++) {
			out.print(times[i]);
			out.println(" " + keys[i]);
		}


	}
}