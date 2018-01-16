import crawler.MyCrawler;
import db.DbManager;
import neteasy.JSSecret;
import neteasy.UrlSource;
import thread.MyThreadPool;
import thread.PoolType;

/**
 * The project use to find NetEasy Music
 * Created by liuhu on 18/1/10.
 */
public class Main {
    public static void main(String[] args) {

        MyThreadPool.init();
        DbManager.init();

        MyThreadPool.addFixPool(PoolType.dbtype,10);
        MyThreadPool.addFixPool(PoolType.filetype,5);

//        JSSecret.init();
//        String content = UrlSource.getUrlSource("64126");

/*test thread and db
        for(int i = 0; i < 1000; i++) {

            SongEntity songEntity = new SongEntity();
            songEntity.setName("nishishuoooooo"+i);
            songEntity.setId(i);

            TaskInPool.addDoTaskToPool(PoolType.dbtype, new UpdateOrInsert<SongEntity>(songEntity));
        }

        MyThreadPool.removeAllPool();
        System.exit(1);
*/

        MyCrawler myCrawler = new MyCrawler("foundMusic", true);
        try {
            myCrawler.start(4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;

    }
}
