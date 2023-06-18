package test.operation;

import com.crehana.catalog.dto.CityDTO;
import com.crehana.catalog.exception.CrehanaException;
import com.crehana.catalog.persistence.CityPersistence;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;

public class PersistenceTest {
    @Test
    public void givenValidFile_whenReadFile_thenReturnsListOfCityDTO() {

        // Arrange (Preparación)
        CityPersistence cityPersistence = new CityPersistence();

        // Act (Acción)
        List<CityDTO> cityList = cityPersistence.readFile();

        // Assert (Verificación)
        Assert Assertions = null;
        Assertions.assertNotNull(cityList);
        Assertions.assertFalse(cityList.isEmpty());
        // Realiza más aserciones para verificar si los datos se leyeron correctamente
    }

    @Test
    public void givenNonExistentFile_whenReadFile_thenThrowsCrehanaException() {

        // Arrange (Preparación)
        CityPersistence cityPersistence = new CityPersistence();

        // Act (Acción) y Assert (Verificación)
        Assertions.assertThrows(CrehanaException.class, () -> {
            cityPersistence.readFile();
        }, "Se esperaba que el método readFile() lance una excepción del tipo CrehanaException");

        // Puedes agregar más aserciones para verificar el mensaje de error específico de la excepción lanzada
    }

    @Test
    public void testCreateCity() {
        // Given
        CityDTO city = new CityDTO();
        city.setCode("SCL");
        city.setName("Santiago de Chile");

        CityPersistence persistence = new CityPersistence();

        // When
        persistence.create(city);

        // Then
        List<CityDTO> cities = persistence.getAll();
        Assert.assertEquals(cities.size(), 1); // Se espera que haya una ciudad en la persistencia
        Assert.assertEquals(cities.get(0).getCode(), "SCL"); // Se espera que el código de la ciudad coincida
        Assert.assertEquals(cities.get(0).getName(), "Santiago de Chile"); // Se espera que el nombre de la ciudad coincida
    }

    @Test
    public void testCreate_CityCreatedSuccessfully() {
        // Given
        CityDTO city = new CityDTO("Gye", "Guayaquil");
        CityPersistence persistence = new CityPersistence();

        // When
        persistence.create(city);

        // Then
        List<CityDTO> cities = persistence.getAll();
        Assert.assertTrue(cities.contains(city));
    }




}


