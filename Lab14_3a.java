import java.util.Random;

// SumThread calculates sum of squares for a portion of the array
class SumSquareThread extends Thread {

    int[] array;
    int start;
    int end;
    long result = 0; // stores partial sum

    public SumSquareThread(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        // sum squares from start index up to end index not including that end index
        for (int i = start; i < end; i++) {
            result += (long) array[i] * array[i];
        }
    }
}

public class Lab14_3a {

    //fill array with random integers between 1 and 100
    static int[] generateArray(int n) {
        Random random = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(100) + 1;
        }
        return array;
    }

    //non-threaded sum of squares using loop
    static long nonThreadedSum(int[] array) {
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += (long) array[i] * array[i];
        }
        return sum;
    }

    //threaded sum of squares split halves into two threads 
    static long threadedSum(int[] array) throws InterruptedException {
        int mid = array.length / 2;

        //thread1 handles first half while thread2 handles second half
        SumSquareThread thread1 = new SumSquareThread(array, 0, mid);
        SumSquareThread thread2 = new SumSquareThread(array, mid, array.length);

        thread1.start();
        thread2.start();

        //wait for both threads to finish 
        thread1.join();
        thread2.join();

        //combines sums of thwo threads
        return thread1.result + thread2.result;
    }

    public static void main(String[] args) throws InterruptedException {

        // n values to test
        int[] testSizes = {100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

        // creating a neat table to compare results
        System.out.printf("%-15s %-20s %-20s%n", "n", "Non-Threaded (ms)", "Threaded (ms)");
        System.out.println("--------------------------------------------------------------");

        for (int n : testSizes) {

            int[] array = generateArray(n);

            //    Non-threaded timing 
            long startTime = System.nanoTime();
            long sum1 = nonThreadedSum(array);
            long endTime = System.nanoTime();
            double nonThreadedMs = (endTime - startTime) / 1_000_000.0; //nano seconds to ms

            //Threaded timing 
            startTime = System.nanoTime();
            long sum2 = threadedSum(array);
            endTime = System.nanoTime();
            double threadedMs = (endTime - startTime) / 1_000_000.0;

            //print results for n
            System.out.printf("%-15d %-20.4f %-20.4f%n", n, nonThreadedMs, threadedMs);
        }
    }
}
//with shorter arrays the method with a single thread is quicker but as the array size gets larger the method with two threads starts to become more efficient