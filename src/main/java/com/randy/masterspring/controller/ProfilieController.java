package com.randy.masterspring.controller;

import com.randy.masterspring.date.USLocalDateFormatter;
import com.randy.masterspring.model.ProfileForm;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLConnection;
import java.util.Locale;

/**
 * Created by randy on 2017/7/16.
 */
@Controller
public class ProfilieController {

    private static final Resource PICTURES_DIR = new FileSystemResource("./pictures");

    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("/images/anonymous.png");
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(classPathResource.getFilename()));
        IOUtils.copy(classPathResource.getInputStream(),response.getOutputStream());
    }
    @RequestMapping("upload")
    public String uploadPage(){
        return "profile/uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException{

        if (file.isEmpty() || !isImage(file)) {
            redirectAttributes.addFlashAttribute("error","Incorrect file. Please upload a picture");
            return "redirect:/upload";
        }
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
