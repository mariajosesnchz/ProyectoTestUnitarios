package com.crehana.catalog.exception;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class CrehanaExceptionTest {
    @Test
    public void testCrehanaExceptionMessagePropagation() {
        // Arrange
        String shortDescription = "Error occurred";
        CrehanaException exception = new CrehanaException(shortDescription);

        // Act
        String actualMessage = exception.getMessage();

        // Assert
        Assert.assertEquals(shortDescription, actualMessage);
    }

    @Test
    public void testCrehanaExceptionCreation_Success() {
        // Arrange
        String shortDescription = "Error occurred";

        // Act
        CrehanaException exception = new CrehanaException(shortDescription);

        // Assert
        Assert.assertEquals(shortDescription, exception.getMessage());
    }
}
