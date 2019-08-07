package demo.example.blogdemo.controller;

import demo.example.blogdemo.model.PdfReport;
import demo.example.blogdemo.model.PdfReport1;
import demo.example.blogdemo.model.Post;
import demo.example.blogdemo.service.AuthorService;
import demo.example.blogdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private AuthorService authorService;
    @GetMapping("/post")
    public String create(Model model){
        model.addAttribute("post",new Post());
        model.addAttribute("authors",authorService.findall());
        return "postForm";
    }
    @PostMapping("/post")
    public String process(@Valid Post post, BindingResult result,RedirectAttributes redirectAttributes){
       if(result.hasErrors()){
           return "postForm";
       }
       postService.create(post);
       redirectAttributes.addFlashAttribute("insert",true);
       return "redirect:/posts";
    }
    @GetMapping("/posts")
    public String showAllPosts(Model model){
        model.addAttribute("allposts",postService.findAll());
        model.addAttribute("success",model.containsAttribute("success"));
        model.addAttribute("insert",model.containsAttribute("insert"));
        return "posts";
    }
    @GetMapping("/posts/details/{id}")
    public String showDetails(Model model,@PathVariable("id")long id) {
        model.addAttribute("post",postService.findById(id));
        return "postDetails";
    }
    @GetMapping("/posts/update/{id}")
    public String updatePost(@PathVariable("id") long id,Model model){
        this.updateId=id;
        model.addAttribute("post",postService.findById(id));
        model.addAttribute("authors",authorService.findall());
        return "postUpdateForm";
    }
    @PostMapping("/posts/update")
    public String processPost(RedirectAttributes redirectAttributes,Post post){
        postService.update(updateId,post);
        redirectAttributes.addFlashAttribute("success",true);
        return "redirect:/posts";
    }
    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable("id") Long id){
        postService.delete(id);
        return "redirect:/posts";
    }

    private Long updateId;

    @GetMapping(value="/pdfreport",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdfReport(){
        ByteArrayInputStream bis= PdfReport.postPdfViews(postService.findAll());
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition","inline;filename=postreport.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }



}
