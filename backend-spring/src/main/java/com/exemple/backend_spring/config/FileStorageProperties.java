package com.exemple.backend_spring.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class FileStorageProperties {

    @Value("${upload.dir}")
    private String uploadDir;


}

