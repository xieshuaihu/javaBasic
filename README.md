# javaBasic
主要记录一些java中常用的基础知识、算法、设计模式

## 1.printABC
多线程依次打印ABC  
主要知识点：Semaphore  
Semaphore.acquire();  
Semaphore.release();  

## 2.Singleton
双重检查锁实现单例模式  
主要知识点：单例模式  
创建一个静态volatile变量，然后在加锁前后都进行检查变量是否为null  
