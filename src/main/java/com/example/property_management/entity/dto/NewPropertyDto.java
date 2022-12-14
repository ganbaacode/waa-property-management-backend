package com.example.property_management.entity.dto;

import com.example.property_management.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPropertyDto {

    private String type;
    private String name;
    private String image;
    private Property property;
}
