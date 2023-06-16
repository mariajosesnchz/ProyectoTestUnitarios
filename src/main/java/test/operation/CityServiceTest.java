package test.operation;

import com.crehana.catalog.dto.CityDTO;
import com.crehana.catalog.persistence.CityPersistence;
import com.crehana.catalog.service.CityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;


import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertTrue;

public class CityServiceTest {
    @Test
    public void testExistCity() {
        // Given
        String cityCode = "C001";
        CityDTO existingCity = new CityDTO(cityCode, "City A");

        // Mock the persistence layer
        CityPersistence mockPersistence = mock(CityPersistence.class);
        when(mockPersistence.existCity(cityCode)).thenReturn(Optional.of(existingCity));

        // Create an instance of CityService with the mock persistence
        CityService cityService = new CityService(mockPersistence);

        // When
        Optional<CityDTO> result = cityService.existCity(cityCode);

        // Then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(existingCity, result.get());
    }
    @Test
    void testExistCityForNonExistentCity() {
        // Arrange
        String nonExistentCityCode = "ABC123";
        CityPersistence persistence = Mockito.mock(CityPersistence.class);
        CityService cityService = new CityService(persistence);

        // Mock behavior
        when(persistence.existCity(anyString())).thenReturn(Optional.empty());

        // Act
        Optional<CityDTO> result = cityService.existCity(nonExistentCityCode);

        // Assert
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testUpdateCity() {
        // Arrange
        CityDTO existingCity = new CityDTO("ABC123", "Old City");
        CityDTO updatedCity = new CityDTO("ABC123", "New City");

        CityPersistence persistence = Mockito.mock(CityPersistence.class);
        CityService cityService = new CityService(persistence);

        // Mock behavior
        when(persistence.existCity(anyString())).thenReturn(Optional.of(existingCity));

        // Act
        cityService.update(updatedCity);

        // Assert
        Mockito.verify(persistence).update(updatedCity);
    }


}
