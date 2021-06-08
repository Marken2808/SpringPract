package com.home.bookstore.controller;

import com.home.bookstore.model.Book;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping("")
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

    @RequestMapping(value ="/register", method = RequestMethod.POST)
    public String RegisterPage(){

        return "pages/register";
//        return "redirect:/login";
    }

}