package com.example.WebsiteMHiepBe.service.Plastic;

import com.example.WebsiteMHiepBe.dao.GenreRepository;
import com.example.WebsiteMHiepBe.dao.ImageRepository;
import com.example.WebsiteMHiepBe.dao.PlasticItemReposiroty;
import com.example.WebsiteMHiepBe.entity.Genre;
import com.example.WebsiteMHiepBe.entity.Image;
import com.example.WebsiteMHiepBe.entity.PlasticItem;
import com.example.WebsiteMHiepBe.service.UploadImage.UploadImageService;
import com.example.WebsiteMHiepBe.utils.Base64ToMultipartFileConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.hibernate.Internal;
import org.hibernate.query.UnknownParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlasticServiceImp implements PlasticService{
    private final ObjectMapper objectMapper;
    @Autowired
    private PlasticItemReposiroty plasticItemReposiroty;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ImageRepository imageRepository;;
    @Autowired
    private UploadImageService uploadImageService;

    public PlasticServiceImp(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }





    @Override
    @Transactional
    public ResponseEntity<?> update(JsonNode plasticJson) {
        try {
            // Convert JSON to PlasticItem object
            PlasticItem plasticItem = objectMapper.treeToValue(plasticJson, PlasticItem.class);
            List<Image> imageList = imageRepository.findImageByPlasticItem(plasticItem);
            // luu the loai cua plastic
            List<Integer> idGenreList = objectMapper.readValue(plasticJson.get("idGenres").traverse(), new TypeReference<List<Integer>>() {
            });
            List<Genre> genreList = new ArrayList<>();
            for (int idGenre : idGenreList) {
                Optional<Genre> genre = genreRepository.findById(idGenre);
                genre.ifPresent(genreList::add);
            }
            plasticItem.setListGenres(genreList);
            // kiem tra xem thumnail thay doi khong
            String dataThumbnail = formatStringByJson(String.valueOf(plasticJson.get("thumbnail")));
            if(Base64ToMultipartFileConverter.isBase64(dataThumbnail)){
                for(Image image : imageList){
                    if(image.isThumbnail()){
                        // xoa thumbnail cu
                        MultipartFile multipartFile = Base64ToMultipartFileConverter.convert(dataThumbnail);
                        String thumbnailUrl = uploadImageService.uploadImage(multipartFile, "Plastic_" + plasticItem.getIdPlasticItem());
                        image.setUrlImage(thumbnailUrl);
                        imageRepository.save(image);
                        break;
                    }
                }
            }
            PlasticItem newPlasticItem = plasticItemReposiroty.save(plasticItem);
            List<String> arrRelatedImg = objectMapper.readValue(
                    formatStringByJson(String.valueOf(plasticJson.get("relatedImg"))),
                    new TypeReference<List<String>>() {}
            );
            boolean isCheckDelete = true;
            for(String img : arrRelatedImg){
                if(!Base64ToMultipartFileConverter.isBase64(img)){
                    isCheckDelete = false;

                }
            }
            if(isCheckDelete){
                imageRepository.deleteIImagesWithFalseThumnailByPlaticId(newPlasticItem.getIdPlasticItem());
                Image thumnailTemp = imageList.get(0);
                imageList.clear();
                imageList.add(thumnailTemp);
                for (int i = 0; i < arrRelatedImg.size(); i++) {
                    String imgData = arrRelatedImg.get(i);
                   Image image = new Image();
                   image.setPlasticItem(newPlasticItem);
                   image.setThumbnail(false);
                   MultipartFile relatedImgFile = Base64ToMultipartFileConverter.convert(imgData);
                     String url = uploadImageService.uploadImage(relatedImgFile, "Plastic_" + newPlasticItem.getIdPlasticItem() + "." + i);
                     image.setUrlImage(url);
                     imageList.add(image);
                }
            }else {
                // neu khong xoa anh lien quan
                for (int i = 0; i < arrRelatedImg.size(); i++) {
                    String imgData = arrRelatedImg.get(i);
                    if (Base64ToMultipartFileConverter.isBase64(imgData)) {
                        Image image = new Image();
                        image.setPlasticItem(newPlasticItem);
                        image.setThumbnail(false);
                        MultipartFile relatedImgFile = Base64ToMultipartFileConverter.convert(imgData);
                        String url = uploadImageService.uploadImage(relatedImgFile, "Plastic_" + newPlasticItem.getIdPlasticItem() + "." + i);
                        image.setUrlImage(url);
                        imageRepository.save(image);
                    }
                }
            }
            newPlasticItem.setListImages(imageList);

            plasticItemReposiroty.save(newPlasticItem);
            return ResponseEntity.ok("Update plastic item success!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating plastic item: " + e.getMessage());
        }
    }
    @Override
    @Transactional
    public ResponseEntity<?> save(JsonNode plasticJson) {
        try {
            PlasticItem plasticItem = objectMapper.treeToValue(plasticJson, PlasticItem.class);


            // Lưu thể loại
            List<Integer> idGenreList = objectMapper.readValue(plasticJson.get("idGenres").traverse(), new TypeReference<List<Integer>>() {
            });
            List<Genre> genreList = new ArrayList<>();
            for (int idGenre : idGenreList) {
                Optional<Genre> genre = genreRepository.findById(idGenre);
                genreList.add(genre.get());
            }
            plasticItem.setListGenres(genreList);

            PlasticItem plasticItemNew = plasticItemReposiroty.save(plasticItem);
            // Lưu thumbnail cho ảnh
            String dataThumbnail = formatStringByJson(String.valueOf(plasticJson.get("thumbnail")));
            Image thumnail = new Image();
            thumnail.setPlasticItem(plasticItemNew);
            thumnail.setThumbnail(true);
            MultipartFile multipartFile = Base64ToMultipartFileConverter.convert(dataThumbnail);
            String thumbnailUrl = uploadImageService.uploadImage(multipartFile, "Plastic_" + plasticItemNew.getIdPlasticItem());
            thumnail.setUrlImage(thumbnailUrl);
            List<Image> imagesList = new ArrayList<>();
            imagesList.add(thumnail);
            // Lưu các ảnh liên quan
            String relatedImg = formatStringByJson(String.valueOf(plasticJson.get("relatedImg")));
            List<String> relatedImgs = objectMapper.readValue(relatedImg, new TypeReference<List<String>>() {});
            for (int i = 0; i < relatedImgs.size(); i++) {
                String imgData = relatedImgs.get(i);
                MultipartFile imgFile = Base64ToMultipartFileConverter.convert(imgData);
                String url = uploadImageService.uploadImage(imgFile, "Plastic_" + plasticItemNew.getIdPlasticItem() + "." + i);

                Image image = new Image();
                image.setPlasticItem(plasticItemNew);
                image.setThumbnail(false);
                image.setUrlImage(url);
                imagesList.add(image);
            }
            plasticItemNew.setListImages(imagesList);
            // cap nhat lai plastic item
            plasticItemReposiroty.save(plasticItemNew);
            return ResponseEntity.ok("Update plastic item success!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public long getTotalPlastic() {
        return plasticItemReposiroty.count();
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(plasticItemReposiroty.findAll());
    }
    private String formatStringByJson(String json) {
        return json.replaceAll("\"", "");
    }
}
