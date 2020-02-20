/* Author: Devansh Amin */
import java.util.concurrent.*;
import java.util.Random;

public class Parallelism {
    static long sum;
    static class Adder implements Runnable{
        int hi,lo;
        int[] arr;
        Adder(int[] arr, int lo, int hi){
            this.arr = arr;
            this.lo = lo;
            this.hi = hi;
        }
        @Override
        public void run() {
            synchronized (this) {
                //System.out.println("Thread Name: "+Thread.currentThread().getName());
                //System.out.println("Low: "+lo);
                //System.out.println("High: "+hi);
                for (int i = lo; i < hi; i++) {
                    sum += arr[i];
                }
            }
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor;
        Adder fst;
        Future task;
        Thread first;
        long start,end;
        Random rand = new Random();
        int limit = 250_000_000,lo=0,hi=0,temp=0;
        int[] a = new int[limit];

        for(int i=0;i<limit;i++){
            a[i] = rand.nextInt();
        }

        for(int i=1;i<=100;i++) {
            if (limit % i == 0) {
                temp = limit / i;
                executor = Executors.newFixedThreadPool(i);
                lo = 0;
                hi = temp;
                start = System.currentTimeMillis();
                for (int j = 0; j < i; j++) {
                    
                    fst = new Adder(a, lo, hi);
                    first = new Thread(fst);
                    first.start();
                    first.join();
                    //task = executor.submit(new Adder(a, lo, hi));
                    //task.get();
                    lo = hi;
                    hi += temp;
                }
                end = System.currentTimeMillis();
                System.out.println("Using " + i + " threads");
                System.out.println("The sum was " + sum);
                System.out.println("The computation took " + (end - start) + " milliseconds\n");
                sum = 0;
            }
        }
    }
}
