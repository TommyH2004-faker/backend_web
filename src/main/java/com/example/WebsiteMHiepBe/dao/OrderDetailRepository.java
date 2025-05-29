package com.example.WebsiteMHiepBe.dao;

import com.example.WebsiteMHiepBe.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "order-details")
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    // Define any custom query methods if needed
}
