package com.bvigentas.drone_delivery.infrastructure;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StringUtilsTest {

    @Test
    void whenCleanSpecialCharacter_givenADirtString_shouldReturnCleanString() {
        //Arrange
        var input = "[Drone A]";

        //Act
        var result = StringUtils.cleanSpecialCharacter(input);

        //Assert
        assertEquals("Drone A", result);
    }

    @Test
    void whenCleanSpecialCharacter_givenACleanString_shouldReturnWithNotDifference() {
        //Arrange
        var input = "Drone A";

        //Act
        var result = StringUtils.cleanSpecialCharacter(input);

        //Assert
        assertEquals(input, result);
    }

}
