JumpHash
========

Jump Consistent Hashing algorithm in Java

Abstract from the paper http://arxiv.org/abs/1406.2294:

> We present jump consistent hash, a fast, minimal memory, consistent hash algorithm that can be expressed in about  5 lines of code. In comparison to the algorithm of Karger et al., jump consistent hash requires no storage, is  faster, and does a better job of evenly dividing the key space among the buckets and of evenly dividing the workload  when the number of buckets changes. Its main limitation is that the buckets must be numbered sequentially, which  makes it more suitable for data storage applications than for distributed web caching.

Uses Guava library for unsigned longs.

Usage
=====

    int bucket = JumpHash.consistent(nrOfBuckets, myObject);

Download
========

Available on [Maven central](http://search.maven.org/#search%7Cga%7C1%7Cjumphash):

 - com.streametry:jumphash:1.0

Requires Java 1.8
    
Build
=====

    gradle jar
    

License
=======

MIT
