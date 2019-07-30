package demo.example.blogdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Data

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "{author.name.validator.msg}")
    private String name;
    @Past(message = "date of birth must be past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NotEmpty(message = "please enter something")
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
