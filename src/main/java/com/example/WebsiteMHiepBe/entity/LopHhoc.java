package com.example.WebsiteMHiepBe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LopHhoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String maLop;
}
