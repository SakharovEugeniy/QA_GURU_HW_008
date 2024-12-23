import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {


    @BeforeAll
    static void allPagesPreConditionSetup() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void allPagesPostCondition() {
        Selenide.closeWebDriver();
    }
}
