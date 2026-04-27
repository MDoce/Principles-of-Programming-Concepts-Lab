class SumRun implements Runnable {
    //stores sum
    int sum = 0;

    @Override
    public void run() {
        // gets sum of numbers 1-100 added together
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
    }
}

public class Lab14_1a {
    public static void main(String[] args) {

        //make object from Runnable class
        SumRun sumRun = new SumRun();

        //give the object to a thread to do the task
        Thread thread = new Thread(sumRun);

        // start thread
        thread.start();

        // join() so the main thread waits here until thread finishes
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
            Thread.currentThread().interrupt();
        }

        //prints sum
        System.out.println("Sum of numbers 1 to 100: " + sumRun.sum);
    }
}