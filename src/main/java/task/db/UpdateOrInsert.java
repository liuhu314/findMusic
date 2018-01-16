package task.db;

import db.DbManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by liuhu on 18/1/10.
 */
public class UpdateOrInsert<T> implements Runnable {

    T entity = null;

    public UpdateOrInsert(T arg) {

        entity = arg;
    }

    @Override
    public void run() {
        Session session = DbManager.getDbSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(entity);
            transaction.commit();
        }
        catch (Exception e)
        {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {

        }
    }
}
