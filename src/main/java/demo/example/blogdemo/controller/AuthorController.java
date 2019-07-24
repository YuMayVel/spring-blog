package demo.example.blogdemo.controller;

import demo.example.blogdemo.model.Author;
import demo.example.blogdemo.service.AuthorService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @GetMapping("/author")
    public String create(Model model){

    model.addAttribute("author",new Author());


        return "authorForm";
    }
    @PostMapping("/author")
    public String process (Author author, BindingResult result){
       if(result.hasErrors()){
           return "authorForm";
       }
       authorService.create(author);
       return "redirect/authors";
    }
    @GetMapping("/authors")
    public String showallAuthors(Model model){
        model.addAttribute("authors",authorService.findall());
        return "authors";
    }
}
