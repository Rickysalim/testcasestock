package com.example.stock.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemStock")
    private Integer itemStock;

    @Column(name = "itemSerialNumber")
    private String itemSerialNumber;

    @Column(name = "itemAdditionalInfo")
    private String itemAdditionalInfo;

    @Column(name = "itemPicture")
    private String itemPicture;

    // @CreationTimestamp
    // @Temporal(TemporalType.DATE)
    @Column(name = "itemCreatedAt")
    private Date itemCreatedAt;

    @Column(name = "itemCreatedBy")
    private String itemCreatedBy;

    // @UpdateTimestamp
    // @Temporal(TemporalType.DATE)
    @Column(name = "itemUpdatedAt")
    private Date itemUpdatedAt;

    @Column(name = "itemUpdatedBy")
    private String itemUpdatedBy;
    

    public Item(String itemName, Integer itemStock, String itemSerialNumber, String itemAdditionalInfo, String itemPicture, Date itemCreatedAt, String itemCreatedBy,  Date itemUpdatedAt, String itemUpdatedBy) {
        this.itemName = itemName;
        this.itemStock = itemStock;
        this.itemSerialNumber = itemSerialNumber;
        this.itemAdditionalInfo = itemAdditionalInfo;
        this.itemPicture = itemPicture;
        this.itemCreatedAt = itemCreatedAt;
        this.itemCreatedBy = itemCreatedBy;
        this.itemUpdatedAt = itemUpdatedAt;
        this.itemUpdatedBy = itemUpdatedBy;
    }
}