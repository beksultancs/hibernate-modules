package peaksoft.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

/**
 * @author Beksultan
 */
@Embeddable
@Getter @Setter
@ToString
public class Capital {
    private String capitalName;
    private Long population;
    private Long code;
}
