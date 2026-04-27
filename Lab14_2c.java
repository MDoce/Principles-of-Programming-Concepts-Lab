class Counter2c {
    int count = 0;
    public synchronized void increment() {
        count++;
    }
}
public class Lab14_2c {
    public static void main(String[] args) {
        Counter2c counter = new Counter2c();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {

            threads[i] = new Thread(() -> { for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });

            threads[i].start();
        }

    // no join anymore

        System.out.println("Final counter value: " + counter.count + " Correct: 10000");
    }
}