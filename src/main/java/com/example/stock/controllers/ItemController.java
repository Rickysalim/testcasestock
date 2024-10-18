package com.example.stock.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.dto.ItemDTO;
import com.example.stock.model.Item;
import com.example.stock.services.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemService itemService;
    

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // http://localhost:8081/api/items
    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        return itemService.getAllItems();
    }

    // http://localhost:8081/api/item/1
    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id")  Integer id) {
        return itemService.getItemById(id);
    }

    // http://localhost:8081/api/item
	@PostMapping(value="/item")
	public ResponseEntity<Item> createItem(@RequestBody @Valid ItemDTO item) {
        return itemService.createItem(item);
	}

    // http://localhost:8080/api/item/1
	@PutMapping("/item/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable("id") Integer id, @RequestBody @Valid ItemDTO item) {
		return itemService.updateItem(id, item);
	}

   // http://localhost:8080/api/item/1
	@DeleteMapping("/item/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Integer id) {
		return itemService.deleteItem(id);
	}

    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
