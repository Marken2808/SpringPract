package com.home.bookstore.controller;

import com.home.bookstore.model.Book;
import com.home.bookstore.model.Role;
import com.home.bookstore.model.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @RequestMapping("/")
    public String HomePage(){
        return "pages/home";
    }

    @RequestMapping("/login")
    public String LoginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null || authentication instanceof AnonymousAuthenticationToken){
            return "pages/login";
        }
        return "redirect:/";
    }

    @RequestMapping("/register")
    public String RegisterPage(Model model){
        User user = new User();
        model.addAttribute(user);
        return "pages/register";
    }



}