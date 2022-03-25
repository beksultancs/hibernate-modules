package peaksoft.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "mamleketter")
@Getter
@Setter
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Capital capital;

}
