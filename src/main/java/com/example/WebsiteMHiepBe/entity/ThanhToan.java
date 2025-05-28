package com.example.WebsiteMHiepBe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "thanh_toan")
public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phuongThuc;
    private String trangThai;
    private LocalDateTime ngayThanhToan;

    @OneToOne
    @JoinColumn(name = "don_hang_id")
    private DonHang donHang;
}

