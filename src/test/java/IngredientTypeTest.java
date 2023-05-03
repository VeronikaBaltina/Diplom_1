import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {
    @Test
    public void TestIngredientTypeSauce() {
        assertNotNull(IngredientType.valueOf("FILLING"));
    }

    @Test
    public void TestIngredientTypeFilling() {
        assertNotNull(IngredientType.valueOf("SAUCE"));
    }
}
