package demo.example.blogdemo.service;

import demo.example.blogdemo.model.Post;
import javafx.geometry.Pos;

import javax.validation.constraints.Max;
import java.util.List;

public interface PostService {

    Post create(Post post);

    Post findById(Long id);

    List<Post> findAll();

    void update(Long id,Post post);

    void delete(Long id);

}
