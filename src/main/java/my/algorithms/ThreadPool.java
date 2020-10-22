package my.algorithms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private static class Worker implements Runnable {
        private BlockingQueue<Runnable> jobs;

        public Worker(BlockingQueue<Runnable> jobs) {
            this.jobs = jobs;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Runnable job = jobs.take();
                    job.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    private BlockingQueue<Runnable> jobs;

    public ThreadPool(int size) {
        jobs = new LinkedBlockingQueue<>();

        for (int i = 0; i < size; i++) {
            new Thread(new Worker(jobs)).start();
        }
    }

    public void execute(Runnable job) {
        try {
            jobs.put(job);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
