package hello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Prime {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(getPrimesBetween(1, 10000));
        System.out.println(getPrimesBetween(1, 10000, 4));
        System.out.println(1 + " / 3 with cast to double is " + (1 / 3d));
    }

    private static class PrimeCallable implements Callable<List<Integer>> {

        private int lower;
        private int upper;

        public PrimeCallable(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrimesBetween(lower, upper);
        }
    }

    /**
     * Calculate the primes in between two numbers but all threads running in parallel on different
     * cores and returning the result in parallel.
     * 
     * @param lower
     * @param upper
     * @param coreCount
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static List<Integer> getPrimesBetween(int lower, int upper, int coreCount) throws InterruptedException, ExecutionException {
        int numbersToEachCore = (upper - lower) / coreCount;
        ExecutorService service = Executors.newFixedThreadPool(coreCount);
        int start = lower;
        List<Future<List<Integer>>> futureList = new ArrayList<Future<List<Integer>>>(coreCount);
        for (int i = 0; i < coreCount; i++) {
            futureList.add(service.submit(new PrimeCallable(start, start + numbersToEachCore)));
            start += numbersToEachCore + 1;
        }
        List<Integer> primes = new LinkedList<Integer>();
        for (Future<List<Integer>> future : futureList) {
            primes.addAll(future.get());
        }
        Collections.sort(primes);
        service.shutdown();
        return primes;
    }

    /**
     * Just calculate the primes in between two numbers
     * 
     * @param lower
     * @param upper
     * @return
     */
    public static List<Integer> getPrimesBetween(int lower, int upper) {
        List<Integer> primes = new LinkedList<Integer>();
        for (int current = lower; current <= upper; current++) {
            boolean isPrime = true;
            for (int i = 2; i * i <= current; i++) {
                if (current % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(current);
            }
        }
        return primes;
    }
}
