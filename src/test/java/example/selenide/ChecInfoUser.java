package example.selenide;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ChecInfoUser {
    @Test
    public void checInfoUser() {
        $(By.xpath("//*[@id=\"under-slider-menu\"]/div/a[2]/i")).click();
        $(By.xpath("//*[@id=\"info\"]/div/div[1]/div[2]/div[2]/span[2]")).shouldHave(text("Хусаенов Амир Ильдусович"));
        $(By.xpath("//*[@id=\"info\"]/div/div[1]/div[2]/div[3]/span[2]")).should(text("19.04.2001"));
        $x("//*[@id=\"info\"]/div/div[1]/div[2]/div[6]/span[2]").shouldBe(text("ИВМиИТ"));
    }
}
