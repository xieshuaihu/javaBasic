import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class printABCWaitNotify {
    //静态变量，通过Object里的wait和notify实现线程同步，state决定打印状态
    private static int state = 0;
    private static Object lock = new Object();

    public static void main(String[] atrgs) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new Printer("A", 0));
        pool.execute(new Printer("B", 1));
        pool.execute(new Printer("C", 2));
        pool.shutdown();
    }
    //静态内部类
    private static class Printer implements Runnable {
        private String a;
        private int localstate;
        public Printer(String a, int n){
            this.a = a;
            this.localstate = n;
        }
        public void run() {
            for(int i = 0; i < 100; ++i) {
                synchronized(lock)
                {
                    while (state != localstate) {
                        try {lock.wait();} catch(Exception ex) {ex.printStackTrace();}
                    }
                    if (state == localstate) {
                        System.out.print(a);
                        state = (state + 1) % 3;
                        try {lock.notifyAll();} catch(Exception ex) {ex.printStackTrace();}
                    }
                }
            }
        }
    }
}
