/* Author: Devansh Amin */
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Parallelism {

    public static void main(String[] args) throws Exception{
        // Make a random Array
        Random random = new Random();
        int[] xs = new int[250_000_000];
        for (int i = 0; i < xs.length; i += 1) xs[i] = random.nextInt();

        // Try different numbers of threads from 1 up to 100.
        for (int i = 1; i <= 100; i+=1) {
            if (xs.length%i != 0) continue;
            System.out.println("Using " + i + " threads");
            long st = System.currentTimeMillis();

            AtomicLong sum = new AtomicLong(0);
            // Make i different threads.  Each one adds up one section of the array.
            // Start each one.
            Thread[] threads = new Thread[i];
            for (int t = 0; t < i; t += 1) {
                int lo = t * (xs.length / i);
                int up = lo + (xs.length / i);
                Runnable task = () -> {
                    long minisum = 0;
                    for (int j = lo; j < up; j += 1) {
                        minisum += xs[j];
                    }
                    sum.getAndAdd(minisum);
                };
                threads[t] = new Thread(task);
            }

            for (Thread thread : threads) thread.start();
            for (Thread thread : threads) thread.join();

            long ed = System.currentTimeMillis();
            System.out.println("The sum was " + sum.get());
            System.out.println("The computation took "+(ed-st)+" milliseconds");
            System.out.println();
        }
    }
}
