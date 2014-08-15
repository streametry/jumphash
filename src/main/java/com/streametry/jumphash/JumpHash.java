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
package com.streametry.jumphash;

import static com.google.common.primitives.UnsignedLong.ONE;
import static com.google.common.primitives.UnsignedLong.fromLongBits;
import static com.google.common.primitives.UnsignedLong.valueOf;

import com.google.common.primitives.UnsignedLong;

public class JumpHash {

	static UnsignedLong constant = valueOf(2862933555777941757L);
	static long constant2 = 1L<<31;

	public static int consistent(int buckets, Object ob) {
		return consistent(buckets, ob.hashCode());
	}

	/** Jump Consistent Hashing algorithm **/
	public static int consistent(int buckets, long keyHash) {

		UnsignedLong key = fromLongBits(keyHash);

		long b = -1, j = 0;
		while( j < buckets ) {
			b = j;

			key = key.times(constant).plus( ONE );
			UnsignedLong keyShift = fromLongBits( key.longValue() >>> 33 ).plus( ONE );

			j = (long)( (b+1) * ( constant2 / keyShift.doubleValue() ) );
		}

		return (int) b;
	}
}
