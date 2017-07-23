package com.randy.masterspring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "upload.pictures")
public class PictureUploadProperties {
    private Resource uploadPath;
    private Resource anonymousPicture;

    public Resource getAnonymousPicture(){
        return anonymousPicture;
    }

    public Resource getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(Resource uploadPath) {
        this.uploadPath = uploadPath;
    }

    public void setAnonymousPicture(Resource anonymousPicture) {
        this.anonymousPicture = anonymousPicture;
    }
}
