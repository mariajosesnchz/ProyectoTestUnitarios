package test.operation;

import com.crehana.catalog.dto.CityDTO;
import com.crehana.catalog.persistence.CityPersistence;
import com.crehana.catalog.service.CityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Optional;

public class CityServiceTest {
    @Test
    public void testExistCity() {
        // Given
        String cityCode = "C001";
        CityDTO existingCity = new CityDTO(cityCode, "City A");

        // Mock the persistence layer
        CityPersistence mockPersistence = Mockito.mock(CityPersistence.class);
        Mockito.when(mockPersistence.existCity(cityCode)).thenReturn(Optional.of(existingCity));

        // Create an instance of CityService with the mock persistence
        CityService cityService = new CityService(mockPersistence);

        // When
        Optional<CityDTO> result = cityService.existCity(cityCode);

        // Then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(existingCity, result.get());
    }
}
