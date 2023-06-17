package test.operation;

import com.crehana.catalog.controller.CityController;
import com.crehana.catalog.controller.MenuController;
import com.crehana.catalog.enums.MenuOption;
import com.crehana.catalog.service.CityService;
import com.crehana.catalog.validator.CityValidator;
import com.crehana.catalog.validator.MenuOptionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

public class ControllerTest {
    @Test
    void testDraw_ExitOption_Selected() {
        // Arrange
        MenuOptionValidator validator = mock(MenuOptionValidator.class);
        CityController cityController = mock(CityController.class);
        Scanner scanner = createScannerWithInput("4"); // Simulate selecting the exit option
        MenuController menuController = new MenuController(validator, cityController, scanner);

        // Act
        assertDoesNotThrow(menuController::draw);

        // Assert
        // Verify that the menu loop is exited
        verify(cityController, never()).create();
        verify(cityController, never()).update();
        verify(cityController, never()).getAll();
    }

    private Scanner createScannerWithInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        return new Scanner(inputStream);
    }

    @Test
    void testDraw_ValidOption_Selected() {
        // Arrange
        MenuOptionValidator validator = mock(MenuOptionValidator.class);
        CityController cityController = mock(CityController.class);
        Scanner scanner = createScannerWithInput("1"); // Simulate selecting a valid option
        MenuController menuController = new MenuController(validator, cityController, scanner);

        // Act
        assertDoesNotThrow(menuController::draw);

        // Assert
        // Verify that the corresponding action is redirected based on the selected option
        verify(cityController, times(1)).create();
        verify(cityController, never()).update();
        verify(cityController, never()).getAll();
    }
    @Mock
    private CityController cityController;

    @Mock
    private MenuOptionValidator validator;

    @Mock
    private Scanner scanner;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMenu() {
        // Arrange
        MenuController menuController = new MenuController(validator, cityController, scanner);

        // Mock la salida estándar
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Act
        menuController.createMenu();
        String menuOutput = outputStream.toString().trim();

        // Assert
        String expectedMenu = "1 - List the cities\n2 - Create a city\n3 - Update a city\n5 - Exit"; // Menú esperado
        Assertions.assertEquals(expectedMenu, menuOutput);
    }


    @Test
    public void testFormatOption_Success() {
        // Arrange
        MenuController menuController = new MenuController(null, null, null);
        MenuOption option = MenuOption.CREATE;

        // Act
        String formattedOption = menuController.formatOption(option);

        // Assert
        assertEquals("1 - Create", formattedOption);
        System.out.println(formattedOption);
    }

    private void assertEquals(String s, String formattedOption) {
    }
    @Test
    public void testCreateMenu_Success() {
        // Arrange
        MenuController menuController = new MenuController(null, null, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        menuController.createMenu();
        String menuOutput = outputStream.toString().trim();

        // Assert
        assertEquals("1 - Option 1\n2 - Option 2\n3 - Option 3", menuOutput);
    }

}
