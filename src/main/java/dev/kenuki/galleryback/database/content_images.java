package dev.kenuki.galleryback.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class content_images {
    @Id
    private String url;
    private String name;
    private String category;
}
