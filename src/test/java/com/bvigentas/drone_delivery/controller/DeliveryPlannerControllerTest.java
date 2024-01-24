package com.bvigentas.drone_delivery.controller;

import com.bvigentas.drone_delivery.application.service.impl.DeliveryPlannerServiceImpl;
import com.bvigentas.drone_delivery.presentation.controller.impl.DeliveryPlannerControllerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryPlannerControllerTest {


    public static final String EXPECTED_OUTPUT = "EXPECTED_OUTPUT";
    @Mock
    DeliveryPlannerServiceImpl service;

    @InjectMocks
    DeliveryPlannerControllerImpl controller;

    @Test
    void whenPlanDeliveryIsCalled_ShouldReturnResponseEntityWithExpectedString() throws IOException {
        //Arrange
        var file = new MockMultipartFile("input.txt", "teste".getBytes());
        when(service.planDelivery(file)).thenReturn(EXPECTED_OUTPUT);

        //Act
        var result = controller.planDelivery(file);

        //Assert
        assertEquals(ResponseEntity.ok(EXPECTED_OUTPUT), result);
    }

}
