
public class ParallelTask implements Runnable {

	double result = 0;

	double taskNo = Math.random() * 100;

	public void run() {
		System.out.println("Starting task No " + Math.floor(taskNo));

		for (int i = 0; i < 200; i++)
			result = Math.sin(i);
		System.out.println("finishing task No " + Math.floor(taskNo));
	}
}
