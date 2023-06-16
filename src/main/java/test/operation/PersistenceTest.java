package test.operation;

import com.crehana.catalog.dto.CityDTO;
import com.crehana.catalog.exception.CrehanaException;
import com.crehana.catalog.persistence.CityPersistence;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;


import java.util.List;

public class PersistenceTest {
    @Test
    public void givenValidFile_whenReadFile_thenReturnsListOfCityDTO() {
        // Objetivo: Verificar que el método readFile() pueda leer y convertir correctamente el contenido del archivo cities.json en una lista de objetos CityDTO.
        // Capa: Persistencia
        // Clase: CityPersistence
        // Método: readFile()
        // Cuando: Se intenta leer el archivo cities.json
        // Dado: Que el archivo cities.json existe y contiene datos válidos
        // Entonces: Se espera que el método readFile() devuelva una lista de objetos CityDTO que contenga los datos del archivo cities.json

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
        // Objetivo: Verificar que el método readFile() maneje adecuadamente la excepción cuando el archivo cities.json no existe.
        // Capa: Capa de persistencia
        // Clase: CityPersistence
        // Método: readFile()
        // Cuando: Se intenta leer el archivo cities.json, pero el archivo no existe en la ubicación especificada
        // Dado: No es necesario proporcionar datos de entrada adicionales en este caso
        // Entonces:
        // - Se espera que el método readFile() lance una excepción del tipo CrehanaException
        // - Se espera que la excepción tenga el mensaje de error correspondiente a la inexistencia del archivo (PersistenceConstants.FILE_NOT_EXISTS)

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

}


