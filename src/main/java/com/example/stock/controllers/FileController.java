package com.example.stock.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.stock.model.Item;
import com.example.stock.services.FileService;



@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
   
    @PostMapping(value="/upload/file/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> uploadFile(@PathVariable("id") Integer id,  @RequestPart(value = "file", required = false) MultipartFile file) {
        return fileService.uploadFile(id, file);
	}

}
