package com.example.WebsiteMHiepBe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "danh_muc")
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenDanhMuc;
}
