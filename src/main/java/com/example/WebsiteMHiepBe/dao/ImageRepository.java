package com.example.WebsiteMHiepBe.dao;

import com.example.WebsiteMHiepBe.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "images")
public interface ImageRepository extends JpaRepository<Image, Integer> {
    // Define any custom query methods if needed
}
