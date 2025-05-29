package com.example.WebsiteMHiepBe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private int idGenre;

    @Column(name = "name_genre")
    private String nameGenre;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "plastic_item_genre", joinColumns = @JoinColumn(name = "id_genre"), inverseJoinColumns = @JoinColumn(name = "id_plastic_item"))
    private List<PlasticItem> listPlasticItems;
}