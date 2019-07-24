package demo.example.blogdemo.service;

import demo.example.blogdemo.model.Author;
import demo.example.blogdemo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author create(Author author) {

        return authorRepository.save(author);
    }

    @Override
    public Author findById(Long id) {

        return authorRepository.getOne(id);
    }

    @Override
    public List<Author> findall() {

        return authorRepository.findAll();
    }
}
