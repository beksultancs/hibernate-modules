package beksultan_peaksoft;

import beksultan_peaksoft.models.Car;
import beksultan_peaksoft.models.EngineType;
import beksultan_peaksoft.repositories.CarRepo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        CarRepo carRepo = new CarRepo();
//        carRepo.dropCarTable();
//        carRepo.createCarTable();

        Car car = new Car(
                null,
                "Hyundai",
                "Sonata LF",
                50000,
                EngineType.FUEL
        );
//        carRepo.save(car);
        carRepo.deleteById(2L);

        carRepo.findAll().forEach(System.out::println);
    }
}
