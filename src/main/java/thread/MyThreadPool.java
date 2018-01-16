package thread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuhu on 18/1/10.
 */
public class MyThreadPool {

    /**
     * 线程池容器
     */
    private static HashMap<String,ExecutorService> poolMap = null;

    /**
     * 初始化线程池容器
     */
    public static void init()
    {
        poolMap = new HashMap<String, ExecutorService>();
    }

    /**
     * 开辟一个固定数量线程池
     */
    public static void addFixPool(String name,int count)
    {
        poolMap.put(name,Executors.newFixedThreadPool(count));
    }


    /**
     * 添加一个自动扩展型线程池
     * @param name
     */
    public static void addCachePool(String name)
    {
        poolMap.put(name,Executors.newCachedThreadPool());
    }

    /**
     * 移除一个类型的线程池
     *
     */
    public static void removePoolFromName(String name)
    {
        if(poolMap.containsKey(name))
        {
            poolMap.get(name).shutdown();

            poolMap.remove(name);
        }
    }

    public static void removeAllPool()
    {
        Iterator<ExecutorService> it = poolMap.values().iterator();

        while(it.hasNext())
        {
            it.next().shutdown();
            it.remove();
        }

    }

    /**
     * 获取某个功能线程池
     * @param name
     * @return
     */
    public static ExecutorService getPoolFromName(String name)
    {
        if(poolMap.containsKey(name))
        {
            return poolMap.get(name);
        }

        return null;
    }
}
