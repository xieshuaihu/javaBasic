## 1.printABCSemaphore
多线程依次打印ABC  
主要知识点：Semaphore  
Semaphore.acquire();  
Semaphore.release();   
## 2.printABCWaitNotify  
通过Wait和Notify方法实现多线程打印ABC  
主要知识点：Wait，Notify，Synchronized  
只有获得了对象的锁，才可以调用对象的Wait方法；在调用wait后，会让出对象锁；在调用notifyAll后会让所用wait的线程解除wait状态，重新参与锁的竞争，只有再次获得锁后才会继续向下运行。  
## 3.printABCCondition  
通过ReentrantLock及Condition实现多线程打印ABC  
主要知识点：ReentrantLock，Condition  
与通过Wait和Notify实现打印ABC的思路一致，ReentrantLock.lock()等价于synchronized(Object), Condition.await()等价于Object.wait(), Condition.signalAll()等价于Object.notifyAll();  
## 4.printABCReentrantLock  
仅通过ReentrantLock实现多线程打印ABC  
主要知识点：ReentrantLock
ReentrantLock是可重入锁，但是重入多少次，就要离开多少次。即调用了多少次lock.lock()就要调用多少次lock.unlock().这也是ReentrantLock与Synchronized的一个主要区别，Synchronized也是可重入锁，但是无论重入多少次，离开一次就可以了。
## Summary  
控制对资源的并发访问 | 实现线程同步   
-- | --  
Synchronized | wait, notify 
ReentrantLock| await, signal  

因为wait，notify或await，signal仅能实现两个线程的同步，所以在实现三个线程打印ABC的时候，额外引入了一个变量state用来表示哪个线程应该被唤醒。  
Semaphore属于线程同步原语，不涉及对资源的并发访问，在一些场景中需要额外引入锁控制资源的并发访问。  
