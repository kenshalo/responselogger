package squareup.responselogger;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Collects a series of responses.
 * 
 * @author Kenshalo
 * 
 */
public class ResponseCollector {

	private static final int DEFAULT_CAPACITY = 10;
	private int aSum = 0;
	private float ave = 0f;
	private Queue<Integer> vals = null;
	private float var = 0f;

	/**
	 * Default constructor
	 */
	public ResponseCollector() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs a response collector that stores the number specified in the
	 * passed capacity parameter
	 * 
	 * @param capacity
	 *            the capacity
	 */
	public ResponseCollector(int capacity) {
		this.vals = new ArrayBlockingQueue<Integer>(capacity);
	}

	public float getAve() {
		return ave;
	}

	public float getVar() {
		return var;
	}

	private void init(int v) {
		while (vals.offer(v)) {
			aSum += v;
		}
	}

	/**
	 * Processes a value, calculates the updated average and variance, and
	 * returns the average and variance in a float array. The average is the
	 * first value in the array and the variance is the second.
	 * 
	 * <pre>
	 * ReponseCollector c = new ResponseCollector();
	 * int someValue;
	 * c.processValue(someValue);
	 * 
	 * </pre>
	 * 
	 * @param v
	 *            the new value to process
	 * @return the average and variance
	 */
	public synchronized void processValue(int v) {
		if (vals.size() == 0) {
			init(v);
		}

		int y1 = vals.remove();
		vals.offer(v);
		aSum += v - y1;

		// the average
		int n = vals.size();
		this.ave = (float) aSum / n;

		// the variance
		float vSum = 0f;
		for (Iterator<Integer> it = vals.iterator(); it.hasNext();) {
			vSum += Math.pow((ave - it.next()), 2);
		}
		this.var = vSum / n;
	}
}
