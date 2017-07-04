package com.randy.masterspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by randy on 2017/6/11.
 */
@Controller
@SpringBootApplication
public class Application {
    @RequestMapping("/")
    String home(Model model){
        model.addAttribute("message","hello from the contrller");
        return "resultPage";
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
