package com.example.WebsiteMHiepBe.service.UploadImage;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UploadImageImp implements UploadImageService {
    private final Cloudinary cloudinary;
    @Override
    public String uploadImage(MultipartFile multipartFile, String name) {
        // Implement the logic to upload the image and return the URL
        return "Image uploaded successfully"; // Placeholder return value
    }

    @Override
    public void deleteImage(String imgUrl) {
        // Implement the logic to delete the image using the provided URL
    }
}
