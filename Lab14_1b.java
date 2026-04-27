class SumThread extends Thread {

    //store sum
    int sum = 0;

    // so we can name threads (not really necessary here)
    public SumThread(String name) {
        super(name); 
    }

    @Override
    public void run() {
        // gets sum of numbers 1-100 added together
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
    }
}

public class Lab14_1b {
    public static void main(String[] args) {

        // make a new thread
        SumThread sumThread = new SumThread("Thread1");

        // starts thread
        sumThread.start();

        // join() so the main thread waits here until thread finishes
        try {
            sumThread.join(); 
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
            Thread.currentThread().interrupt();
        }

        //print sum
        System.out.println("Sum of numbers 1 to 100: " + sumThread.sum);
    }
}