import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class printABCReentrantLock {
    private static volatile int state = 0;
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new worker("A", 0));
        pool.execute(new worker("B", 1));
        pool.execute(new worker("C", 2));
        pool.shutdown();
    }

    private static class worker implements Runnable {
        private String ch;
        private int localstate;
        public worker(String ch, int localstate) {
            this.ch = ch;
            this.localstate = localstate;
        };
        public void run() {
            for (int i = 0; i < 100; ++i) {
                while (true) {
                    lock.lock();
                    if (localstate == state) {
                        System.out.print(ch);
                        state = (state + 1) % 3;
                        lock.unlock();
                        break;
                    }
                    lock.unlock();
                }
            }
        }
    }
}
