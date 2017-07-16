package com.randy.masterspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by randy on 2017/7/16.
 */
@Controller
public class ProfilieController {

    @RequestMapping("profile")
    public String displayProfile(){
        return "profile/profilePage";
    }
}
