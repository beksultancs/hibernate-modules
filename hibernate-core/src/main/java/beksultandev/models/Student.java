package beksultandev.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "students")
@Getter @Setter
public class Student {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String email;
    private int age;
    private LocalDate dateOfBirth;
    @Enumerated()
    private Groups group;
}
