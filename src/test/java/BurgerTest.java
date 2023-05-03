import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    Burger burger;

    @Before
    public void init() {
        burger = new Burger();
    }

    @Test
    public void TestSetBuns() {
        Bun bun = Mockito.mock(Bun.class);
        burger.setBuns(bun);
        assertEquals("Неверная булка", bun, burger.bun);
    }

    @Test
    public void TestAddIngredient() {
        List<Ingredient> ingredients = List.of(Mockito.mock(Ingredient.class), Mockito.mock(Ingredient.class), Mockito.mock(Ingredient.class));
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        assertEquals("Некорректные ингридиенты", ingredients, burger.ingredients);
    }

    @Test
    public void TestRemoveIngredient() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Assert.assertTrue("Список ингридиентов не пустой", burger.ingredients.isEmpty());
    }

    @Test
    public void TestMoveIngredient() {
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient1.getName()).thenReturn("filling cutlet");
        Mockito.when(ingredient2.getName()).thenReturn("sauce sour cream");
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(1, 0);
        assertEquals("Некорректный первый ингридиент", "sauce sour cream", burger.ingredients.get(0).getName());
        assertEquals("Некорректный второй ингридиент", "filling cutlet", burger.ingredients.get(1).getName());
    }

    @Test
    public void TestGetPrice() {
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(ingredient1.getPrice()).thenReturn(100.00f);
        Mockito.when(ingredient2.getPrice()).thenReturn(101.00f);
        Mockito.when(bun.getPrice()).thenReturn(100.00f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals("Некорректная сумма прайса", 401.00f, burger.getPrice(), 0.01f);
    }

    @Test
    public void TestGetReceipt() {
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(ingredient1.getPrice()).thenReturn(100.00f);
        Mockito.when(ingredient2.getPrice()).thenReturn(101.00f);
        Mockito.when(ingredient1.getName()).thenReturn("cutlet");
        Mockito.when(ingredient2.getName()).thenReturn("sour cream");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(bun.getPrice()).thenReturn(100.00f);
        Mockito.when(bun.getName()).thenReturn("black bun");
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.setBuns(bun);
        String expected = "(==== black bun ====)\r\n" +
                "= filling cutlet =\r\n" +
                "= sauce sour cream =\r\n" +
                "(==== black bun ====)\r\n" +
                "\r\n" +
                "Price: 401,000000\r\n";
        assertEquals("Неверный чек", expected, burger.getReceipt());
    }
}
