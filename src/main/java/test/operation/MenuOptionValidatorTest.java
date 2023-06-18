package test.operation;

import com.crehana.catalog.validator.MenuOptionValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuOptionValidatorTest {
    @Test
    public void testIsValid_NullOption_ReturnsFalse() {
        // Objetivo: Verificar que se invalide correctamente una opción nula utilizando el método isValid() de MenuOptionValidator.
        // Capa: Validador
        // Clase: MenuOptionValidator
        // Método: isValid()
        // Cuando: Se llama al método isValid() de MenuOptionValidator con una opción nula.
        // Dado: Una opción nula.
        // Entonces: Se espera que el método retorne false.

        // Dado
        MenuOptionValidator validator = new MenuOptionValidator();
        String option = null;

        // Cuando
        boolean isValid = validator.isValid(option);

        // Entonces
        assertFalse(isValid);
    }
    @Test
    public void testIsValid_ValidOption_ReturnsTrue() {
        // Objetivo: Verificar que se valide correctamente una opción válida utilizando el método isValid() de MenuOptionValidator.
        // Capa: Validador
        // Clase: MenuOptionValidator
        // Método: isValid()
        // Cuando: Se llama al método isValid() de MenuOptionValidator con una opción válida.
        // Dado: Una opción válida.
        // Entonces: Se espera que el método retorne true.

        // Dado
        MenuOptionValidator validator = new MenuOptionValidator();
        String option = "1";

        // Cuando
        boolean isValid = validator.isValid(option);

        // Entonces
        assertTrue(isValid);
    }
}
