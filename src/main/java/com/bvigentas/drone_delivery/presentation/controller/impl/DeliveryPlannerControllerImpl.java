package com.bvigentas.drone_delivery.presentation.controller.impl;

import com.bvigentas.drone_delivery.application.service.DeliveryPlannerService;
import com.bvigentas.drone_delivery.presentation.controller.DeliveryController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/delivery-planner")
public class DeliveryPlannerControllerImpl implements DeliveryController {

    private final DeliveryPlannerService deliveryPlannerService;

    @PostMapping
    public ResponseEntity<String> planDelivery(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(deliveryPlannerService.planDelivery(file));
    }

}
