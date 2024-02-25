package dev.kenuki.galleryback;

import dev.kenuki.galleryback.database.ContentImagesRepository;
import dev.kenuki.galleryback.database.content_images;
import dev.kenuki.galleryback.database.content_images_url_projection;
import dev.kenuki.galleryback.services.ImageStorage;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserApi {
    private final ContentImagesRepository contentImagesRepository;
    private final ImageStorage imageStorageService;
    @Autowired
    public UserApi(ContentImagesRepository contentImagesRepository, ImageStorage imageStorage){
        this.contentImagesRepository = contentImagesRepository;
        this.imageStorageService = imageStorage;
    }
    @GetMapping("/")
    public List<String> getContentImages(){
        List<content_images_url_projection> urls = contentImagesRepository.findAllBy();
        List<String> arr = new ArrayList<>();
        for (content_images_url_projection url : urls) {
            arr.add(url.getUrl());
        }
        return arr;
    }
    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename){
        Resource resource = imageStorageService.loadFile(filename);
        if(resource == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
    @PostMapping("/images")
    public String uploadImage(@RequestParam("file") MultipartFile file){
        System.out.println("Storing file ");
        return imageStorageService.storeFile(file);

    }
}
