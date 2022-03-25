package peaksoft.repositories;

import peaksoft.configuration.DatabaseConnection;
import peaksoft.models.Country;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Beksultan
 */
public class CountryRepo2 {

    private final EntityManager entityManager =
            DatabaseConnection.getEntityManager();

    public void save(Country country) {
        entityManager.getTransaction().begin();
        entityManager.persist(country);
        entityManager.getTransaction().commit();
    }

    public List<Country> findAll() {
        entityManager.getTransaction().begin();
        List<Country> resultList =
                entityManager.createQuery("select c from Country c")
                        .getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    public Country findById(Long id) {
        entityManager.getTransaction().begin();
        Country country = entityManager.find(Country.class, id);
        entityManager.getTransaction().commit();
        return country;
    }

    public void deleteAll() {
        entityManager.getTransaction().begin();;
        entityManager.createQuery("delete from Country ").executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void deleteById(Long id) {
        entityManager.getTransaction().begin();;
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    public void update(Long id, Country newCountry) {
        entityManager.getTransaction().begin();
        Country country = entityManager.find(Country.class, id);
        country.setName(newCountry.getName());
        country.setCapital(newCountry.getCapital());
        entityManager.getTransaction().rollback();
    }

}
