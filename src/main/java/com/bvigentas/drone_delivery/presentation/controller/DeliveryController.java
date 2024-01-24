package com.bvigentas.drone_delivery.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DeliveryController {

    ResponseEntity<String> planDelivery(@RequestParam("file") MultipartFile file) throws IOException;

}
