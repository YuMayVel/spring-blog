package demo.example.blogdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String interested;
    @Enumerated(EnumType.STRING)
    private Gender gender;


    public Author(String name, LocalDate dateOfBirth, String interested, Gender gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.interested = interested;
        this.gender = gender;
    }
    public Author(){

    }
}
