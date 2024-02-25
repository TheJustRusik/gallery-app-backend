package dev.kenuki.galleryback;

import dev.kenuki.galleryback.configuration.StorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class)
public class GalleryBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryBackApplication.class, args);
    }

}
