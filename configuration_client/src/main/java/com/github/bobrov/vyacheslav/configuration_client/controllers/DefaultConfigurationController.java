package com.github.bobrov.vyacheslav.configuration_client.controllers;

import com.github.bobrov.vyacheslav.configuration_client.services.ConfigurationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.lang.String.format;

@Controller
@RefreshScope
@Slf4j
public class DefaultConfigurationController {
    @Autowired
    private ConfigurationService configurationService;

    @Value("${param1}")
    private String param1;

    @Value("${param2}")
    private String param2;

    @Value("${param3}")
    private String param3;

    @RequestMapping("/upload")
    public @ResponseBody
    String upload(String configuration) throws RestException {
        try {
            configurationService.load(configuration);

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
