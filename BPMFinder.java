public class BPMFinder {

	public static void main(String[] args) {
		String filename = args[0];
		In in = new In(filename);
		// Out out = new Out("clean_" + filename)

		// find number of notes
		int numLines = 0;
		while (!in.isEmpty()) {
			in.readLine();
			numLines++;
		}
		in = new In(filename);

		// calculate average difference in time (BPM)
		double[] times = new double[numLines];
		for (int i = 0; i < times.length; i++) {
			times[i] = Double.parseDouble(in.readLine().split(" ")[0]);
		}
		in = new In(filename);

		double[] diffs = new double[times.length-1];
		for (int i = 0; i < diffs.length; i++) {
			diffs[i] = times[i+1] - times[i];
		}

		double total = 0;
		for (double diff : diffs)
			total += diff;
		double average = total / (double)diffs.length;
		System.out.println(average);

	}
}