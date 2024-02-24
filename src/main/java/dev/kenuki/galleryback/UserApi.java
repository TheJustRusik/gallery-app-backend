package dev.kenuki.galleryback;

import dev.kenuki.galleryback.database.ContentImagesRepository;
import dev.kenuki.galleryback.database.content_images;
import dev.kenuki.galleryback.database.content_images_url_projection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserApi {
    private final ContentImagesRepository contentImagesRepository;
    @Autowired
    public UserApi(ContentImagesRepository contentImagesRepository){
        this.contentImagesRepository = contentImagesRepository;
    }
    @GetMapping("/content_images")
    public List<String> getContentImages(){
        System.out.println("Request worked");
        List<content_images_url_projection> urls = contentImagesRepository.findAllBy();
        List<String> arr = new ArrayList<>();
        for (content_images_url_projection url : urls) {
            arr.add(url.getUrl());
        }
        return arr;
    }
}
