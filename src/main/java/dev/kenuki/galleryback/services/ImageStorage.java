package dev.kenuki.galleryback.services;

import dev.kenuki.galleryback.configuration.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ImageStorage {
    private final Path rootLocation;
    @Autowired
    public ImageStorage(StorageConfig config) {
        this.rootLocation = Paths.get(config.getLocation());
    }
    public String storeFile(MultipartFile file) {
        Path destinationFile = this.rootLocation.resolve(Paths.get(Objects.requireNonNull(file.getOriginalFilename()))).normalize().toAbsolutePath();
        if(destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())){
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Successfully stored file!");
                return "ok";
            } catch (IOException e) {
                System.out.println("Some errors happen");
            }

        }
        return "not ok";
    }
    public Resource loadFile(String fileName){
        try {
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw  new RuntimeException("42 ImageStorage.java {public Resource loadFile(String fileName)}");
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

}
