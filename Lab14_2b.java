class Counter2b {
    int count = 0;
    public synchronized void increment() {
        count++;
    }
}

public class Lab14_2b {
    public static void main(String[] args) {

        Counter2b counter = new Counter2b();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> { for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });

            threads[i].start();
        
            try {
                threads[i].join(); // joins after starting
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Final counter value: " + counter.count + " Correct: 10000");
    }
}