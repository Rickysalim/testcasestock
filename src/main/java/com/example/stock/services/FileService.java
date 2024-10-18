package com.example.stock.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.stock.model.Item;

public interface FileService {
    ResponseEntity<Item> uploadFile(Integer id, MultipartFile file);
}
