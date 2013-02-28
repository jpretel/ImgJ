import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class aea {
	public static void main(String[] args) {

		ParallelTask pt1 = new ParallelTask();
		ParallelTask pt2 = new ParallelTask();
		ParallelTask pt3 = new ParallelTask();
		ParallelTask pt4 = new ParallelTask();
		ParallelTask pt5 = new ParallelTask();
		ParallelTask pt6 = new ParallelTask();
		ExecutorService es = Executors.newFixedThreadPool(2);
		es.execute(pt1);
		es.execute(pt2);
		es.execute(pt3);
		es.execute(pt4);
		es.execute(pt5);
		es.execute(pt6);

		es.shutdown();
	}
}