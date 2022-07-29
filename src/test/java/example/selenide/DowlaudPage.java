package example.selenide;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DowlaudPage {

    @Test
    public void dowlaud(){
        $(byXpath("//*[@id=\"under-slider-menu\"]/div/a[2]")).click();
        $(byXpath("//*[@id=\"info\"]/div/div[1]/div[1]/div[2]/div[2]")).click();
        $(byXpath("//*[@id=\"answer\"]/a[1]")).click();
        $(byXpath("//*[@id=\"info\"]/div/div[1]/div[1]/div[1]/form/div[1]/input")).uploadFile(new File("C:\\Users\\KP\\Downloads\\image.jpg"));
        $(byXpath("//*[@id=\"info\"]/div/div[1]/div[1]/div[1]/form/div[2]/input")).click();
        $(byXpath("//*[@id=\"under-slider-menu\"]/div/a[1]")).click();
        logTest();
    }

    public void logTest(){
        $(byXpath("//*[@id=\"main-blocks\"]/div[2]/div[7]/a")).click();
        $(byText("Документы")).click();
        $(byText("Заселение в общежития КФУ")).click();
        $(byId("doc-templates")).shouldHave(text("Заявление на заселение в общежития КФУ"));
        try {
            File report = $(byXpath("//*[@id=\"tab1\"]/p[3]/a")).download();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
