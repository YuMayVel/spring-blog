package demo.example.blogdemo.controller;

import demo.example.blogdemo.model.Author;
import demo.example.blogdemo.model.PdfReport1;
import demo.example.blogdemo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;

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
    public String process (@Valid Author author, BindingResult result, RedirectAttributes redirectAttributes){
       if(result.hasErrors()){
           return "authorForm";
       }
       authorService.create(author);
       redirectAttributes.addFlashAttribute("success",true);
       return "redirect:/authors";
    }
    @GetMapping("/authors")
    public String showallAuthors(Model model){
        model.addAttribute("authors",authorService.findall());
        model.addAttribute("success",model.containsAttribute("success"));
        return "authors";
    }
    @GetMapping("/author/pdfreport")
    public ResponseEntity<InputStreamResource> showPdfResource(){
        ByteArrayInputStream bis= PdfReport1.authorPdfViews(authorService.findall());
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition","inline;filename=authorReport.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
