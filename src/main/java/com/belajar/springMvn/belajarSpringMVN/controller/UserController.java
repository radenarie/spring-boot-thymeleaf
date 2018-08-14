package com.belajar.springMvn.belajarSpringMVN.controller;

import com.belajar.springMvn.belajarSpringMVN.entity.User;
import com.belajar.springMvn.belajarSpringMVN.sercive.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

//    @RequestMapping(path = "/")
//    public String index() {
//        return "user/index";
//    }

    @GetMapping(value = "/list/create")
    public String viewCreate(Model model) {
        model.addAttribute("User", new User());
        return "user/save";
    }

    @GetMapping(value = "/list/update/{id}")
    public String viewUpdate(Model model, @PathVariable(value = "id") int id) {
        User user = new User();
        user.setId(id);
        model.addAttribute("User",userService.findById(user));
        return "user/Update";
    }

    @GetMapping(value = "/list/delete/{id}")
    public String viewDelete(Model model, @PathVariable(value = "id") int id) {
        User user = new User();
        user.setId(id);
        userService.delete(user);
        model.addAttribute("User",userService.findAll());
        return "user/products";
    }

    @GetMapping(value = "/list")
    public String findAll(Model model) {
        model.addAttribute("User",userService.findAll());
        return "user/products";
    }

    @PostMapping(value = "list")
    public String saveData(User model) {
        User data = userService.save(model);
        return "redirect:/list";
    }
    @PostMapping(value = "update")
    public String updateData(User model) {
        User data = userService.update(model);
        return "redirect:/list";
    }
}
