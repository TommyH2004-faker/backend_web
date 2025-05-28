package com.example.WebsiteMHiepBe.entity;

import com.example.WebsiteMHiepBe.entity.NguoiDung;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "thong_bao")
public class ThongBao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tieuDe;           // tiêu đề thông báo
    private String noiDung;          // nội dung thông báo
    private LocalDateTime thoiGian;  // thời gian gửi

    private boolean daDoc = false;   // người dùng đã đọc hay chưa

    @ManyToOne
    @JoinColumn(name = "nguoi_dung_id")
    private NguoiDung nguoiDung;     // người nhận thông báo


}
