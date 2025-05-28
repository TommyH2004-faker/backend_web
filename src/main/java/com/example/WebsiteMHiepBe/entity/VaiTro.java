package com.example.WebsiteMHiepBe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vai_tro")
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenVaiTro;
}

