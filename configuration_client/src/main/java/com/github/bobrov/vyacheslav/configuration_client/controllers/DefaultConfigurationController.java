package com.github.bobrov.vyacheslav.configuration_client.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RefreshScope
@RestController
@Slf4j
public class DefaultConfigurationController {
    @Value("${param1:n/a}")
    private String param1;

    @Value("${param2:n/a}")
    private String param2;

    @Value("${param3:n/a}")
    private String param3;

    @RequestMapping
    public @ResponseBody
    String showConfig() throws RestException {
        try {
            return format("val1=%s\nval2=%s\nval3=%s\nOK", param1, param2, param3);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @ExceptionHandler(RestException.class)
    public String handleException(RestException e) {
        log.error(e.getLocalizedMessage());
        return "Ошибка: " + e.getMessage();
    }
}
