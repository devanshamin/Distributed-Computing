/* Author: Devansh Amin */

public class Compute_Sum {
	
	static class Adder implements Runnable{
		
		int lo,hi;
		long sum;
		
		Adder(int lo, int hi){
			this.lo=lo;
			this.hi=hi;
		}
		public void run() {
			sum=0;
			System.out.println("Thread Name: "+Thread.currentThread().getName()+"   Thread Id: "+Thread.currentThread().getId());
			for(int i=lo;i<=hi;i++) {
				System.out.println("Number: "+i);
				sum += i;
			}
			System.out.println("Sum of "+lo+" to "+hi+" is "+sum+"\n");
		}
	}

	public static void main(String[] args) {
		System.out.println("Thread Name: "+Thread.currentThread().getName()+"   Thread Id: "+Thread.currentThread().getId()+"\n");
		Adder a = new Adder(1,10);
		Adder b = new Adder(11*11, 20*20);
		Adder c = new Adder(21*21*21, 30*30*30);
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		Thread t3 = new Thread(c);
		try {
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		t3.start();
		t3.join();
		}
		catch(InterruptedException e){
			System.out.println(e);
		}
		finally {
		System.out.println("Finally\n");
		}
	}

}
