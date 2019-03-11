package com.github.bobrov.vyacheslav.springTest.controllers;

import com.github.bobrov.vyacheslav.springTest.services.ConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class DefaultConfigurationController {
    @Autowired
    private ConfigurationService configurationService;

    @RequestMapping
    public @ResponseBody
    String upload(String configuration) throws RestException {
        try {
            configurationService.load(configuration);
            return "OK";
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
