package com.home.bookstore.controller;

import com.home.bookstore.model.Author;
import com.home.bookstore.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/authors")
    public String getAuthors(Model model){
        model.addAttribute("authors", authorService.listAll());
        return "authors/list";
    }

    @RequestMapping("/newAuthor")
    public String newAuthor (Model model){
        Author author = new Author();
        model.addAttribute(author);
        return "authors/newAuthor";
    }

    @RequestMapping(value = "/saveAuthor", method = RequestMethod.POST)
    public String saveAuthor(@ModelAttribute("author") Author author) {
        authorService.save(author);
        return "redirect:/authors";
    }

    @RequestMapping("editAuthor/{id}")
    public ModelAndView EditAuthor(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("id");
        Author author = authorService.get(id);
        mav.addObject("author", author);
        return mav;
    }

    @RequestMapping("deleteAuthor/{id}")
    public String DeleteAuthor(@PathVariable(name = "id") Long id){
        authorService.delete(id);
        return "redirect:/authors";
    }



}
