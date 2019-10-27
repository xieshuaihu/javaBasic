import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class printABCCondition {
    private static int state = 0;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new worker("A", condition, 0));
        pool.execute(new worker("B", condition, 1));
        pool.execute(new worker("C", condition, 2));
        pool.shutdown();
    }

    private static class worker implements Runnable {
        private String ch;
        private Condition con;
        private int localstate;
        public worker(String ch, Condition con, int localstate) {
            this.ch = ch;
            this.con = con;
            this.localstate = localstate;
        };
        public void run() {
            lock.lock();
            for (int i = 0; i < 100; ++i) {
                while (state != localstate) {
                    try {con.await();} catch(Exception ex) {ex.printStackTrace();}
                }
                System.out.print(ch);
                state = (state + 1) % 3;
                try {con.signalAll();} catch(Exception ex) {ex.printStackTrace();}
            }
            lock.unlock();
        }
    }
}
