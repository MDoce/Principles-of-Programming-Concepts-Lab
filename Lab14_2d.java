class Counter2d {

    int count = 0;
    //not synchronized anymore
    public  void increment() {
        count++;
    }
}

public class Lab14_2d {

    public static void main(String[] args) {

        Counter2d counter = new Counter2d();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {

            threads[i] = new Thread(() -> { for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });

            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Final counter value: " + counter.count + " Correct: 10000");
    }
}