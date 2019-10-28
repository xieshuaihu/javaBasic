import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerArrayBlockingQueue {
    private static ArrayBlockingQueue<Integer> storage = new ArrayBlockingQueue<Integer>(10);
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 3; ++i) {
            pool.execute(new Producer());
        }
        for (int i = 0; i < 3; ++i) {
            pool.execute(new Consumer());
        }
        pool.shutdown();
    }
    private static class Producer implements Runnable{
        private Random random = new Random();
        public Producer() {}
        public void run() {
            for (int i = 0; i < 10; ++i) {
                Integer product = Integer.valueOf(random.nextInt(100));
                try
                {
                    storage.put(product);
                    System.out.println("生产者：" + product);
                    Thread.sleep(random.nextInt(30));
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
        public Consumer() {}
        public void run() {
            for (int i = 0; i < 10; ++i) {
                try
                {
                    Integer product = storage.take();
                    System.out.println("消费者：" + product);
                    Thread.sleep(random.nextInt(30));
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
}
