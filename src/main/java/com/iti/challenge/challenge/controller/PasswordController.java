package com.iti.challenge.challenge.controller;

import com.iti.challenge.challenge.dto.PasswordDTO;
import com.iti.challenge.challenge.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniel Abreu
 **/

@RestController
@RequestMapping(value = "api/pwd", produces = MediaType.APPLICATION_JSON_VALUE)
public class PasswordController {

    private final Logger logger = LoggerFactory.getLogger(PasswordController.class);

    @Autowired
    private PasswordService service;

    @RequestMapping(value = "/valid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> valid(@RequestBody PasswordDTO passwordDTO){
        try {
            logger.info("Inicio validar");
            return ResponseEntity.ok().body(service.valid(passwordDTO));
        } catch (IllegalArgumentException ex) {
            logger.error("Password nao esta no formato correto ou nulo.",ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        } catch (Exception e){
            logger.error("Password nao valido.",e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
