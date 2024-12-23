import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Проверка работоспособности страницы сайта pestore")
public class PetSorePageTest extends BaseTest {

    @ValueSource(strings = {
            "Fish", "Dogs", "Reptiles", "Cats", "Birds"
    })
    @ParameterizedTest(name = "Проверка перехода с главной страницы на страницу {0}")
    public void transitionFromTheMainPageToSelectedAnimalPage(String setAnimal) {
        open("https://petstore.octoperf.com/actions/Catalog.action");
        $("area[alt=" + setAnimal + "]").click();
        $("#Catalog").shouldHave(text(setAnimal));
    }
}
