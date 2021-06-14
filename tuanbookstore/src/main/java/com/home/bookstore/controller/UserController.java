package com.home.bookstore.controller;

import com.home.bookstore.model.Role;
import com.home.bookstore.model.User;
import com.home.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/users")
    public String getAuthors(Model model){
        model.addAttribute("users", userService.listAll());
        return "users/list";
    }

    @RequestMapping(value ="/register/test", method = RequestMethod.POST)
    public ModelAndView saveUser(User user){
        ModelAndView mav = new ModelAndView();
        userService.save(user);
        mav.addObject("user", new User());
        mav.setViewName("redirect:/register");
        return mav;
    }

    @RequestMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }

}
