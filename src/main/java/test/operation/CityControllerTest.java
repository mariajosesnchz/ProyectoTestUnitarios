package test.operation;

import com.crehana.catalog.constants.ControllerConstants;
import com.crehana.catalog.controller.CityController;
import com.crehana.catalog.dto.CityDTO;
import com.crehana.catalog.service.CityService;
import com.crehana.catalog.validator.CityValidator;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.mockito.Mockito.*;

public class CityControllerTest {
    @Test
    public void testUpdate_CityUpdatedSuccessfully() {

        // Dado
        CityValidator cityValidatorMock = mock(CityValidator.class);
        Scanner scannerMock = mock(Scanner.class);
        CityService cityServiceMock = mock(CityService.class);
        CityController controller = new CityController(cityValidatorMock, scannerMock, cityServiceMock);

        // Configuración del escenario
        when(scannerMock.next()).thenReturn("BUE", "Buenos Aires"); // Se simula la entrada del código y nombre de la ciudad
        CityDTO city = new CityDTO("BUE", "Buenos Aires");
        when(cityValidatorMock.isValid(city)).thenReturn(true); // Se simula la validación exitosa

        // Cuando
        controller.update();

        // Entonces
        verify(cityServiceMock).update(city); // Se verifica que se llame al método update() de CityService
        // Se verifica que se imprima el mensaje de actualización exitosa
        verify(System.out).println(ControllerConstants.UPDATED_CITY_SUCCESS);

    }

    @Test
    public void testCreate_CityCreatedSuccessfully() {

        CityValidator cityValidatorMock = mock(CityValidator.class);
        Scanner scannerMock = mock(Scanner.class);
        CityService cityServiceMock = mock(CityService.class);
        CityController controller = new CityController(cityValidatorMock, scannerMock, cityServiceMock);

        // Configuración del escenario
        when(scannerMock.next()).thenReturn("Gye", "Guayaquil"); // Se simula la entrada del código y nombre de la ciudad
        CityDTO city = new CityDTO("Gye", "Guayaquil");
        when(cityValidatorMock.isValid(city)).thenReturn(true); // Se simula la validación exitosa

        // Cuando
        controller.create();

        // Entonces
        verify(cityServiceMock).create(city); // Se verifica que se llame al método create() de CityService
        // Se verifica que se imprima el mensaje de creación exitosa
        verify(System.out).println(ControllerConstants.CREATED_CITY_SUCCESS);
    }
}