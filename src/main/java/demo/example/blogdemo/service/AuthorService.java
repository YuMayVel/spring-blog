package demo.example.blogdemo.service;

import demo.example.blogdemo.model.Author;

import java.util.List;

public interface AuthorService {

    Author create(Author author);
    Author findById(Long id);
    List<Author> findall();
}
