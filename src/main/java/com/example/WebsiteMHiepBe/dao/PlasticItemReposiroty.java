package com.example.WebsiteMHiepBe.dao;

import com.example.WebsiteMHiepBe.entity.PlasticItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "plastic-items")
public interface PlasticItemReposiroty extends JpaRepository<PlasticItem, Integer> {
    // Define any custom query methods if needed
}
