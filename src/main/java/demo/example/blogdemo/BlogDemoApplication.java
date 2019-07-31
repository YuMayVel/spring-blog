package demo.example.blogdemo;

import demo.example.blogdemo.model.Author;
import demo.example.blogdemo.model.Gender;
import demo.example.blogdemo.model.Post;
import demo.example.blogdemo.repository.AuthorRepository;
import demo.example.blogdemo.repository.PostRepository;
import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;


@SpringBootApplication
public class BlogDemoApplication {

    private static Logger logger=
            LoggerFactory.getLogger(BlogDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BlogDemoApplication.class, args);

    }
    @Bean @Profile("dev")
    public CommandLineRunner runner(AuthorRepository authorRepository,
                                    PostRepository postRepository){
        return args -> {
            Author author1
                    =new Author("Thaw Thaw",LocalDate.of(2000,3,26),"Horror",Gender.FEMALE);

            Post p1=new Post("jahufkjao","ahfjkjaoe",LocalDate.now());
            Post p2=new Post("jahufkjao","ahfjkjaoe",LocalDate.now());
            Post p3=new Post("jahufkjao","ahfjkjaoe",LocalDate.now());

            p1.setAuthor(author1);
            p2.setAuthor(author1);
            p3.setAuthor(author1);

            authorRepository.save(author1);
            postRepository.save(p1);
            postRepository.save(p2);
            postRepository.save(p3);

            logger.info("successfully");


        };
    }
    @Bean
    public PrettyTime prettyTime(){

        return new PrettyTime();
    }
}
