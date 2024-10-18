package com.example.stock.dto;

import java.sql.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {
    
    private Integer id;

    @NotBlank(message = "Nama Barang Tidak Boleh Kosong")
    private String itemName;

    @Min(message = "Minimal 1", value = 1)
    private Integer itemStock;
    
    @NotBlank(message = "Nomor Serial Tidak Boleh Kosong")
    private String itemSerialNumber;
    
    private String itemAdditionalInfo;

    private Date itemCreatedAt;

    private String itemCreatedBy;

    private Date itemUpdatedAt;

    private String itemUpdatedBy;
    
}