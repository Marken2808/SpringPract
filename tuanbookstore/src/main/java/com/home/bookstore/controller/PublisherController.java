package com.home.bookstore.controller;

import com.home.bookstore.model.Author;
import com.home.bookstore.model.Publisher;
import com.home.bookstore.repositories.PublisherRepository;
import com.home.bookstore.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PublisherController {

    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @RequestMapping("/publishers")
    public String getPublishers(Model model){
        model.addAttribute("publishers", publisherService.listAll());
        return "publishers/list";
    }

    @RequestMapping("/newPublisher")
    public String newPublisher (Model model){
        Publisher publisher = new Publisher();
        model.addAttribute(publisher);
        return "publishers/newPublisher";
    }

    @RequestMapping(value = "/savePublisher", method = RequestMethod.POST)
    public String savePublisher(@ModelAttribute("publisher") Publisher publisher) {
        publisherService.save(publisher);
        return "redirect:/publishers";
    }

    @RequestMapping("editPublisher/{id}")
    public ModelAndView EditPublisher(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("id");
        Publisher publisher = publisherService.get(id);
        mav.addObject("publisher", publisher);
        return mav;
    }

    @RequestMapping("deletePublisher/{id}")
    public String DeletePublisher(@PathVariable(name = "id") Long id){
        publisherService.delete(id);
        return "redirect:/publishers";
    }
}
