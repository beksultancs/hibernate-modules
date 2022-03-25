package peaksoft.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.models.Country;

import javax.persistence.EntityManager;

/**
 * @author Beksultan
 */
public class DatabaseConnection {

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        sessionFactory = new Configuration()
                .setProperty(Environment.DRIVER, "org.postgresql.Driver")
                .setProperty(Environment.URL, "jdbc:postgresql://localhost:5678/postgres")
                .setProperty(Environment.HBM2DDL_AUTO, "update")
                .setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect")
                .setProperty(Environment.SHOW_SQL, "true")
                .addAnnotatedClass(Country.class)
                .buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
