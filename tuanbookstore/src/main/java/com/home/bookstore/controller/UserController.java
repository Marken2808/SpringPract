package com.home.bookstore.controller;

import com.home.bookstore.model.User;
import com.home.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value ="/registerUser", method = RequestMethod.POST)
    public ModelAndView makeRegister(User user){

        ModelAndView mav = new ModelAndView();

        userService.save(user);
        mav.addObject("user", new User());
        mav.setViewName("pages/login");

        return mav;
//        return "redirect:/login";
    }

}
