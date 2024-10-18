package com.example.stock.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import com.example.stock.dto.ItemDTO;
import com.example.stock.model.Item;
import com.example.stock.repositories.ItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    @Autowired
    private final ItemRepository itemRepository;

    @Override
    public ResponseEntity<List<Item>> getAllItems() {
        try {
            List<Item> items = new ArrayList<Item>();

            itemRepository.findAll().forEach(items::add);

            if (items.isEmpty()) {
                return new ResponseEntity<>(items, HttpStatus.OK);
            }
            logger.debug("GET:" + items);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("ERROR:" + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Item> getItemById(Integer id) {
        Optional<Item> itemData = itemRepository.findById(id);

        if (itemData.isPresent()) {
            logger.debug("GET:", itemData.get());
            return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
        } else {
            logger.info("GET:No Content Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Item> createItem(ItemDTO itemDTO) {
        try {
            Item item = itemRepository.save(new Item(itemDTO.getItemName(), itemDTO.getItemStock(), itemDTO.getItemSerialNumber(), itemDTO.getItemAdditionalInfo(), "", itemDTO.getItemCreatedAt(), itemDTO.getItemCreatedBy(), itemDTO.getItemUpdatedAt(), itemDTO.getItemUpdatedBy()));
            logger.debug("CREATED:BERHASIL DIBUAT " + item);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("ERROR:" + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Item> updateItem(Integer id, ItemDTO itemDTO) {
        try {
            Optional<Item> itemData = itemRepository.findById(id);
            if (itemData.isPresent()) {
                Item itemObj = itemData.get();
                itemObj.setItemName(itemDTO.getItemName());
                itemObj.setItemStock(itemDTO.getItemStock());
                itemObj.setItemSerialNumber(itemDTO.getItemSerialNumber());
                itemObj.setItemAdditionalInfo(itemDTO.getItemAdditionalInfo());
                itemObj.setItemPicture("");
                itemObj.setItemCreatedAt(itemDTO.getItemCreatedAt());
                itemObj.setItemCreatedBy(itemDTO.getItemCreatedBy());
                itemObj.setItemUpdatedAt(itemDTO.getItemUpdatedAt());
                itemObj.setItemUpdatedBy(itemDTO.getItemUpdatedBy());
                logger.debug("PUT:BERHASIL DIBUAT " + itemObj);
                return new ResponseEntity<>(itemRepository.save(itemObj), HttpStatus.CREATED);
            } else {
                logger.error("ERROR:Invalid Type File");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("ERROR:" + e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteItem(Integer id) {
        try {
            itemRepository.deleteById(id);
            logger.debug("Info:Delete Success");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("ERROR:" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
