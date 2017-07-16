package com.randy.masterspring.controller;

import com.randy.masterspring.model.ProfileForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by randy on 2017/7/16.
 */
@Controller
public class ProfilieController {

    @RequestMapping("profile")
    public String displayProfile(ProfileForm profileForm){
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(ProfileForm profileForm){
        System.out.print("sava ok");
        return "redirect:/profile";
    }
}
