package thread;

/**
 * Created by liuhu on 18/1/10.
 */
public class TaskInPool {

    public static void addDoTaskToPool(String name, Runnable task)
    {
        if(MyThreadPool.getPoolFromName(name) != null && task != null)
        {
            MyThreadPool.getPoolFromName(name).execute(task);

            return;
        }

        System.out.println("任务执行失败");
    }
}
