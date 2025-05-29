package com.example.WebsiteMHiepBe.dao;

import com.example.WebsiteMHiepBe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Integer> {
    // Define any custom query methods if needed
}
