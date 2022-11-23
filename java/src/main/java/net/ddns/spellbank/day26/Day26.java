package net.ddns.spellbank.day26;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import net.ddns.spellbank.utils.InputFile;

public class Day26 implements Callable<Integer> {
    private final int number;

    public Day26(int number) {
        this.number = number;
    }

    public static void main(String[] args) {
        String file = "day01/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines));
        System.out.println(part2(lines));

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Day26> tasks = new ArrayList<>();
        for (int i = 0; i < 1_000; i++) {
            tasks.add(new Day26(i));
        }

        long time = System.currentTimeMillis();

        List<Future<Integer>> futures = null;
        try {
            futures = executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long sum = 0;
        for (Future<Integer> future : futures) {
            try {
                sum += future.get();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        time = System.currentTimeMillis() - time;

        System.out.println("sum = " + sum + "; time = " + time + " ms");

        executor.shutdown();
    }

    public static long part1(String[] lines) {
        return 0;
    }

    public static long part2(String[] lines) {
        return 0;
    }

    @Override
    public Integer call() throws Exception {
        System.out.printf("Thread %s - Vthread %d waiting...%n", Thread.currentThread().getName(), number);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.printf("Thread %s - Task %d canceled.%n", Thread.currentThread().getName(), number);
            return -1;
        }

        System.out.printf("Thread %s - Task %d finished.%n", Thread.currentThread().getName(), number);
        return ThreadLocalRandom.current().nextInt(100);
    }

}