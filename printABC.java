import java.util.concurrent.Semaphore;

public class printABC {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(1);
        Semaphore sem3 = new Semaphore(1);
        sem1.acquire();
        sem2.acquire();
        Printer printer1 = new Printer(sem3, sem1, "a");
        printer1.start();
        Printer printer2 = new Printer(sem1, sem2, "b");
        printer2.start();
        Printer printer3 = new Printer(sem2, sem3, "c");
        printer3.start();
    }
}

class Printer extends Thread{
    private Semaphore a;
    private Semaphore b;
    private String s;
    public Printer(Semaphore a, Semaphore b, String s){
        this.a = a;
        this.b = b;
        this.s = s;
    };
    public void run(){
        for(int i = 0; i < 100; ++i) {
            try
            {
                a.acquire();
                System.out.print(s);
                b.release();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
