Create an array of 250,000,000 random elements, use different numbers of
threads to compute the sum and compare the run times.
The number of threads changes from 1 to 100. You can only try the numbers that are factors
of 250,000,000 so each thread will compute the same portion of the array. This should be done
using a loop.
There is a global variable “sum" that can be accessed by all the threads. Each of the threads
will add their result to “sum". You need to make sure there is no data race so it will output the
correct sum.
