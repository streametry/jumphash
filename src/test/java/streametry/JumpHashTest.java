/*
The MIT License (MIT)

Copyright (c) 2014 The original author or authors ("Streametry")

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package streametry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Test;


public class JumpHashTest {

	@Test
	public void assignsToSameBucket() throws Exception {

		int bucket1 = JumpHash.consistent(10, "zzz");
		int bucket2 = JumpHash.consistent(11, "zzz");

		assertEquals(bucket1, bucket2);
	}

	@Test
	public void distributesAmongBuckets() throws Exception {

		Map<Integer, Integer> bucketSizes = new TreeMap<>();
		Random r = new Random();
		int max = 100_000;
		int nBuckets = 12;

		for(int i = 0; i < max; i++) {
			int buck = JumpHash.consistent(nBuckets, "key" + r.nextInt());
			bucketSizes.compute(buck, (k, v) -> v == null ? 0 : v + 1 );
		}
		assertEquals(nBuckets, bucketSizes.size());

		IntSummaryStatistics stats = bucketSizes.values().stream().mapToInt( c -> c ).summaryStatistics();

		assertTrue( stats.getMin() > 8000 );
		assertTrue( stats.getMax() < 9000 );
	}
}
