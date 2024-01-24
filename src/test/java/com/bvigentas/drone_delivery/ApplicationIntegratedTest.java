package com.bvigentas.drone_delivery;

import com.bvigentas.drone_delivery.application.DeliveryPlannerServiceTest;
import com.bvigentas.drone_delivery.config.ApplicationContextConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationContextConfiguration.class})
public class ApplicationIntegratedTest {


    private static final String EXPECTED_RESULT = """
                                                    [DroneA]
                                                    Trip #1
                                                    [LocationA]
                                                    Trip #2
                                                    [LocationB], [LocationC]
                                                    Trip #3
                                                    [LocationM], [LocationN], [LocationO], [LocationP]
                                                    
                                                    [DroneB]
                                                    Trip #1
                                                    [LocationD], [LocationE]
                                                    Trip #2
                                                    [LocationF], [LocationG]
                                                    Trip #3
                                                    [LocationH], [LocationI], [LocationJ], [LocationK], [LocationL]
                                                    
                                                    [DroneC]""";

    public static final String URL_ENDPOINT = "/v1/delivery-planner";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void whenPost_GivenValidRequest_ShouldReturnPlannedDeliveryTrip() throws Exception {

        var resourceAsStream = DeliveryPlannerServiceTest.class.getClassLoader().getResourceAsStream("input.txt");
        var file = new MockMultipartFile("file", "input.txt", MediaType.TEXT_PLAIN_VALUE, resourceAsStream.readAllBytes());

        MvcResult result = this.mockMvc.perform(multipart(URL_ENDPOINT).file(file)).andExpect(status().isOk()).andReturn();

        assertEquals(EXPECTED_RESULT, result.getResponse().getContentAsString());

    }

}
