package com.example.WebsiteMHiepBe.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenDangNhap;
    private String matKhau;
    private String hoVaTen;
    private String email;

    @ManyToOne
    @JoinColumn(name = "vai_tro_id")
    private VaiTro vaiTro;
}

