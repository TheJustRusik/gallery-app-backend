package dev.kenuki.galleryback.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentImagesRepository extends JpaRepository<content_images, String> {
    List<content_images_url_projection> findAllBy();
}
