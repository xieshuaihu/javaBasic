import java.util.ArrayDeque;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ProducerConsumer {
    private static int MAX_CAPACITY = 10;
    private static ArrayDeque<Integer> storage = new ArrayDeque<Integer>();
    private static Semaphore empty = new Semaphore(MAX_CAPACITY);
    private static Semaphore full = new Semaphore(0);
    private static Semaphore mutex = new Semaphore(1); //此处也可以使用Synchronized或ReentrantLock
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        pool.execute(new Producer());
        pool.execute(new Producer());
        pool.execute(new Producer());
        pool.execute(new Producer());
        pool.execute(new Producer());
        pool.execute(new Consumer());
        pool.execute(new Consumer());
        pool.execute(new Consumer());
        pool.execute(new Consumer());
        pool.execute(new Consumer());
        pool.shutdown();
    }
    private static class Producer implements Runnable {
        private Random random = new Random();
        private Integer product;
        public Producer() {}
        private int get() {return random.nextInt(100);}
        public void run() {
            for (int i = 0; i < MAX_CAPACITY; ++i) {
                product = Integer.valueOf(get());
                try
                {
                    empty.acquire();
                    mutex.acquire();
                    storage.addLast(product);
                    full.release();
                    System.out.println("生产值: " + product);
                    mutex.release();
                    Thread.sleep(random.nextInt(200));
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    private static class Consumer implements Runnable {
        private Random random = new Random();
        private Integer product;
        public Consumer() {}
        public void run() {
            for (int i = 0; i < MAX_CAPACITY; ++i) {
                try
                {
                    full.acquire();
                    mutex.acquire();
                    product = storage.poll();
                    empty.release();
                    System.out.println("消费值: " + product);
                    mutex.release();
                    Thread.sleep(random.nextInt(200));
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
}
