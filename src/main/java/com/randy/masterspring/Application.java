package com.randy.masterspring;

import com.randy.masterspring.config.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by randy on 2017/6/11.
 */
@Controller
@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class Application extends WebMvcConfigurerAdapter{

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
