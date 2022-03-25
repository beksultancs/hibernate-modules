package peaksoft;

import peaksoft.models.Capital;
import peaksoft.models.Country;
import peaksoft.repositories.CountryRepo;
import peaksoft.repositories.CountryRepo2;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        CountryRepo2 repo2 = new CountryRepo2();
        Country country = new Country();
        country.setName("Canada");

        Capital capital = new Capital();
        capital.setCapitalName("Ottava");
        capital.setPopulation(25000000L);
        capital.setCode(8765432L);

        country.setCapital(capital);

        repo2.save(country);
    }
}
