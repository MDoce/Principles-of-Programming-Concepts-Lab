// Counter class is shared between all threads
class Counter {

    int count = 0;

    //prevent two threads from changing count at same time
    public synchronized void increment() {
        count++;
    }
}

public class Lab14_2a {

    public static void main(String[] args) {

        //all threads share this counter
        Counter counter = new Counter();

        Thread[] threads = new Thread[10];

        // Create and start 10 threads
        for (int i = 0; i < 10; i++) {

            // Each thread increments shared counter 1000 times
            threads[i] = new Thread(() -> { for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });

            threads[i].start();
        }

        // wait for all threads before printing 
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }

        // print
        System.out.println("Final counter value: " + counter.count + " Correct: 10000");
    }
}