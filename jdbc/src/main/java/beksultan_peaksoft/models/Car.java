package beksultan_peaksoft.models;

import java.math.BigDecimal;

/**
 * @author Beksultan
 */
public record Car(
        Long id,
        String brand,
        String model,
        int price,
        EngineType engineType) {
}
