package com.bvigentas.drone_delivery.application;

import com.bvigentas.drone_delivery.application.service.TripOutputPresentationService;
import com.bvigentas.drone_delivery.application.service.impl.DeliveryPlannerServiceImpl;
import com.bvigentas.drone_delivery.application.service.impl.TripOutputPresentationServiceImpl;
import com.bvigentas.drone_delivery.domain.entities.Drone;
import com.bvigentas.drone_delivery.domain.entities.Trip;
import com.bvigentas.drone_delivery.domain.exceptions.MaximumNumberOfDronesLimitExceededException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryPlannerServiceTest {

    private static final String EXPECTED_RESULT = "EXPECTED RESULT";
    @Mock
    TripOutputPresentationServiceImpl outputPresentationService;

    @InjectMocks
    DeliveryPlannerServiceImpl deliveryPlannerService;

    @Test
    void shouldPlanDeliveries() throws IOException {
        var resourceAsStream = DeliveryPlannerServiceTest.class.getClassLoader().getResourceAsStream("input.txt");
        var file = new MockMultipartFile("input.txt", resourceAsStream.readAllBytes());

        when(outputPresentationService.buildDeliveryOutput(anyList(), anyList())).thenReturn(EXPECTED_RESULT);

        var result = deliveryPlannerService.planDelivery(file);

        assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    void shouldThrowErrorOfLimitExceeded() throws IOException {
        var resourceAsStream = DeliveryPlannerServiceTest.class.getClassLoader().getResourceAsStream("invalid_input.txt");
        var file = new MockMultipartFile("input.txt", resourceAsStream.readAllBytes());

        assertThrows(MaximumNumberOfDronesLimitExceededException.class, () -> deliveryPlannerService.planDelivery(file));
    }

}
