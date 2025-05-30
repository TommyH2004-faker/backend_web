package com.example.WebsiteMHiepBe.service.Plastic;

import com.example.WebsiteMHiepBe.dao.GenreRepository;
import com.example.WebsiteMHiepBe.dao.ImageRepository;
import com.example.WebsiteMHiepBe.dao.PlasticItemReposiroty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlasticServiceImp implements PlasticService{
    private final ObjectMapper objectMapper;
    @Autowired
    private PlasticItemReposiroty plasticItemReposiroty;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ImageRepository imageRepository;;






    @Override
    public ResponseEntity<?> save(JsonNode plasticJson) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(JsonNode plasticJson) {
        return null;
    }

    @Override
    public long getTotalPlastic() {
        return 0;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return null;
    }
}
