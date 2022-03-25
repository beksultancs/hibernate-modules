package peaksoft.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.configuration.DatabaseConnection;
import peaksoft.models.Country;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Beksultan
 */
@Transactional
public class CountryRepo {

    private static final SessionFactory sessionFactory =
            DatabaseConnection.getSessionFactory();

    // save
    public void save(Country country) {
        try (Session session = sessionFactory.openSession()) {
            session.save(country);
        }
    }

    // findAll
    public List<Country> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT c FROM Country c")
                    .getResultList();
        }
    }

    // findById
    public Country findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Country.class, id);
        }
    }

    // update
    public void update(Long id, Country newCountry) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Country country = findById(id);
            country.setName(newCountry.getName());
            country.setCapital(newCountry.getCapital());
            session.saveOrUpdate(country);
            session.getTransaction().commit();
        }
    }

    // delete
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Country country = findById(id);
            session.delete(country);
            session.getTransaction().commit();
        }
    }

    // deleteAll
    public void deleteAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Country ")
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    // findByName
    public Country findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return (Country) session
                    .createQuery("select c from Country c where c.name = ?1")
                    .setParameter(1, name)
                    .getSingleResult();
        }
    }
}
