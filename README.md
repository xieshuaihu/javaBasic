# javaBasic
主要记录一些java中常用的基础知识、算法、设计模式  
## 1.printABC
多线程依次打印ABC  
主要知识点：Semaphore, Synchronized(wait, notify), ReentrantLock(await, signal)  
## 2.Singleton
双重检查锁实现单例模式  
主要知识点：单例模式  
创建一个静态volatile变量，然后在加锁前后都进行检查变量是否为null  
## 3.ProducerConsumer  
实现生产者消费者模式  
主要知识点：生产者消费者模式，Semaphore  
一个队列存储值，两个Semaphore分别表示队列的empty和full，在对队列修改的时候需要使用Synchronized或ReentrantLock（本例中使用Semaphore）  
## 4.ProducerConsumerArrayBlockingQueue  
使用阻塞队列实现生产者消费者模式  
主要知识点：ArrayBlockingQueue  
使用put将值添加要队列末尾，如果满了则阻塞等待；使用take去除队首的值，如果为空则阻塞等待。  
