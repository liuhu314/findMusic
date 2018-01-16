package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by liuhu on 18/1/10.
 */
public class DbManager {

    private static SessionFactory sessionFactory = null;

    public static void init()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            Configuration config = new Configuration().configure();
            config.setProperty("hibernate.connection.username","root");
            config.setProperty("hibernate.connection.password","123456");

            sessionFactory = config.buildSessionFactory();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Session getDbSession()
    {
        if(sessionFactory != null) {

            return sessionFactory.getCurrentSession();
        }

        return null;

    }

}
