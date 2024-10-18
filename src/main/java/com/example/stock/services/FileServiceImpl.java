package com.example.stock.services;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.stock.model.Item;
import com.example.stock.repositories.ItemRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private Environment environment;

    @Autowired
    private final ItemRepository itemRepository;

    @Override
    public ResponseEntity uploadFile(Integer id, MultipartFile file) {
        try {
            if(file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FILE TIDAK BOLEH KOSONG");
            }
            String fileType = file.getContentType();
            String filePath = environment.getProperty("env.file_storage")+file.getOriginalFilename();
            Optional<Item> itemData = itemRepository.findById(id);
            if(itemData.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID BARANG TIDAK DITEMUKAN");
            }
            if(fileType.equals("image/png")) {
                if(itemData.isPresent()) {
                    Item itemObj = itemData.get();
                    itemObj.setItemPicture(environment.getProperty("env.file_storage")+file.getOriginalFilename());
                    file.transferTo(new File(filePath));
                    return new ResponseEntity<>(itemRepository.save(itemObj), HttpStatus.CREATED);
                } 
            } 
            if(fileType.equals("image/jpeg")) {
                if(itemData.isPresent()) {
                    Item itemObj = itemData.get();
                    itemObj.setItemPicture(environment.getProperty("env.file_storage")+file.getOriginalFilename());
                    file.transferTo(new File(filePath));
                    return new ResponseEntity<>(itemRepository.save(itemObj), HttpStatus.CREATED);
                } 
            }
      
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TIPE FILE HANYA BOLEH JPG DAN PNG");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
