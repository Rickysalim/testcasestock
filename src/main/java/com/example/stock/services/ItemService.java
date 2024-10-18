package com.example.stock.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.stock.dto.ItemDTO;
import com.example.stock.model.Item;

public interface ItemService {
     
     ResponseEntity<List<Item>> getAllItems();

     ResponseEntity<Item> getItemById(Integer id);

     ResponseEntity<Item> createItem(ItemDTO item);

     ResponseEntity<Item> updateItem(Integer id, ItemDTO itemDTO);

     ResponseEntity<HttpStatus> deleteItem(Integer id);
}
