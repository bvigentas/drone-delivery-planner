package com.bvigentas.drone_delivery.application.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DeliveryPlannerService {

    String planDelivery(MultipartFile file) throws IOException;

}


