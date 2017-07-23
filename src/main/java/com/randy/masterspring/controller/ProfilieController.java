package com.randy.masterspring.controller;

import com.randy.masterspring.date.USLocalDateFormatter;
import com.randy.masterspring.model.ProfileForm;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.util.Locale;

/**
 * Created by randy on 2017/7/16.
 */
@Controller
public class ProfilieController {

    private static final Resource PICTURES_DIR = new FileSystemResource("./pictures");

    @RequestMapping("upload")
    public String uploadPage(){
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file) throws IOException{
        String fileName = file.getOriginalFilename();
        File tempFile = File.createTempFile("pic",getFileExtension(fileName),PICTURES_DIR.getFile());

        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(tempFile)){
            IOUtils.copy(in,out);
        }
        return "profile/uploadPage";
    }


    @RequestMapping("profile")
    public String displayProfile(ProfileForm profileForm){
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "profile/profilePage";
        }
        System.out.print("sava ok");
        return "redirect:/profile";
    }

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale){
        return USLocalDateFormatter.getPattern(locale);
    }

    @RequestMapping(value = "/profile",params = {"addTaste"})
    public String addRow(ProfileForm profileForm){
        profileForm.getTastes().add(null);
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile",params = {"removeTaste"})
    public String removeRow(ProfileForm profileForm, HttpServletRequest request){
        Integer rowId = Integer.valueOf(request.getParameter("removeTaste"));
        profileForm.getTastes().remove(rowId.intValue());
        return "profile/profilePage";
    }

    private boolean isImage(MultipartFile file){
        return file.getContentType().startsWith("image");
    }

    private static String getFileExtension(String name){
        return name.substring(name.lastIndexOf("."));
    }
}
