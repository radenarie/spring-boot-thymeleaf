package com.belajar.springMvn.belajarSpringMVN.controller;

import com.belajar.springMvn.belajarSpringMVN.entity.Profile;
import com.belajar.springMvn.belajarSpringMVN.entity.User;
import com.belajar.springMvn.belajarSpringMVN.sercive.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping(value = "/pro/create")
    public String viewCreate(Model model) {
        model.addAttribute("Profile", new Profile());
        return "profile/save";
    }

    @GetMapping(value = "/pro/update/{id}")
    public String viewUpdate(Model model, @PathVariable(value = "id") int id) {
        Profile profile = new Profile();
        profile.setId(id);
        model.addAttribute("Profile",profileService.findById(profile));
        return "profile/Update";
    }

    @GetMapping(value = "/pro/delete/{id}")
    public String viewDelete(Model model, @PathVariable(value = "id") int id) {
        Profile profile = new Profile();
        profile.setId(id);
        profileService.delete(profile);
        model.addAttribute("Profile",profileService.findAll());
        return "profile/products";
    }

    @GetMapping(value = "/pro")
    public String findAll(Model model) {
        model.addAttribute("Profile",profileService.findAll());
        return "profile/products";
    }

    @PostMapping(value = "pro")
    public String saveData(Profile model) {
        Profile data = profileService.save(model);
        return "redirect:/pro";
    }
    @PostMapping(value = "updates")
    public String updateData(Profile model) {
        Profile data = profileService.update(model);
        return "redirect:/pro";
    }
}


