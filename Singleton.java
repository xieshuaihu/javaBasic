public class Singleton
{
    private volatile static Singleton instance = null;
    private Singleton(){}
    public static Singleton get()
    {
        if(instance == null)
        {
            synchronized(Singleton.instance)
            {
                if(instance == null)
                {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
