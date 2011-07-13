package squareup.responselogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ResponseCollectorTest {

	private ResponseCollector c = new ResponseCollector();
	
	@Before
	public void init() {
		c = new ResponseCollector();
	}
	
	@Test
	public void testProcessValue() {
		c.processValue(200);
		
		Assert.assertEquals("Average test", 200f, c.getAve());
		Assert.assertEquals("Variance test", 0f, c.getVar());
		
		// process some values... 
		// do brute force calculation in test 
		// validate response collector result
		List<Integer> vals = new ArrayList<Integer>(10);
		int sum = 0, t = 10;
		Random random = new Random(System.currentTimeMillis());

		for (int i = 0; i < t; i++) {
			int val = random.nextInt(10);
			vals.add(val);
			sum += val;
			c.processValue(val);
		}

		float eAve = (float) sum / t;
		float v2Sum = 0;
		for (int i = 0; i < vals.size(); i++) {
			v2Sum += Math.pow(eAve - vals.get(i), 2);
		}
		float eVar = v2Sum / t;
		
		Assert.assertEquals("Random numbers average test", eAve, c.getAve());
		Assert.assertEquals("Random numbers variance test", eVar, c.getVar());
	}

}
