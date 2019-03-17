package com.github.bobrov.vyacheslav.configuration_client.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfigurationService {
    public void load(String configuration) {
        log.info("Конфигурация загружена: " + configuration);
    }
}
