package com.example.WebsiteMHiepBe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "anh_san_pham")
public class AnhSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String duongDan;

    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;
}
