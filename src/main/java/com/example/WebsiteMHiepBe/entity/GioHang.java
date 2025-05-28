package com.example.WebsiteMHiepBe.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gio_hang")
public class GioHang {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;

    private int soLuong;
}

