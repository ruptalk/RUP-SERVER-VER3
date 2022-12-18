package com.rup.rup_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShowImageController {

    @GetMapping("/user/user-profile-image/{userUID}")
    public String showProfileImage(@PathVariable String userUID, Model model) {
        model.addAttribute("title", userUID);
        model.addAttribute("imgPath", "/show-user-image/" + userUID + ".jpg");
        return "showImg";
    }

    @GetMapping("/flower/flower-image-png/{flowerNum}")
    public String showFlowerPng(@PathVariable String flowerNum, Model model){
        model.addAttribute("title", flowerNum);
        model.addAttribute("imgPath", "/show-flower-image/" + flowerNum + "/" + flowerNum + ".png");
        return "showImg";
    }

    @GetMapping("/flower/flower-image-gif/{flowerNum}_{grownLevel}")
    public String showFlowerGif(@PathVariable String flowerNum, @PathVariable String grownLevel, Model model){
        model.addAttribute("title", flowerNum);
        model.addAttribute("imgPath", "/show-flower-image/" + flowerNum + "/" + flowerNum + "_" + grownLevel + ".gif");
        return "showImg";
    }
}
