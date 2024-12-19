import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тесты на проверку перевода с английского на руссккий яндекс переводчиком основных частей речи")
public class YandexTranslatorFormTest extends BaseTest {


    @CsvSource(value = {
            "A boy | Мальчик",
            "walked | ходил",
            "alone | один",
            "thoughtfully | вдумчиво",
            "thrice  | трижды",
            "us | нам"
    }, delimiter = '|')
    @ParameterizedTest(name = "Проверить что в Яндекс-переводчике слово {0} на русский переводится как {1}")
    public void yandexTranslatorShouldTranslateAllPartsOfSpeechTest(String searchValue, String resultValue) {
        open("https://translate.yandex.ru/translator/");
        $("#textbox #fakeArea").setValue(searchValue);
        $("#dstBox").shouldHave(text(resultValue));
    }


    static Stream<Arguments> yandexTranslatorMustHaveMultipleWordMeaningsTest() {
        return Stream.of(
                Arguments.of("A boy", List.of("мальчик м", "мальчишка м", "парень м", "юноша м", "пацан м")),
                Arguments.of("walked", List.of("идти", "пойти", "ходить", "шагать", "зашагать")),
                Arguments.of("alone", List.of("один", "сам", "только один")),
                Arguments.of("thoughtfully", List.of("вдумчиво", "внимательно", "заботливо")),
                Arguments.of("thrice", List.of("трижды", "втрое")),
                Arguments.of("us", List.of("США м", "Соединенные Штаты"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка дополнительных значений перевода слова {0}")
    public void yandexTranslatorMustHaveMultipleWordMeaningsTest(String searchValue, List<String> wordMeaning) {
        open("https://translate.yandex.ru/translator/");
        $("#textbox #fakeArea").setValue(searchValue);
        $$("[data-active-target='true']").should(containExactTextsCaseSensitive(wordMeaning));

    }
}
