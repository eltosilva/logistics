package com.silva.elto.logistics.configurions;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Constantes {

    @Value("${url}")
    @Getter
    private String URL;
}
