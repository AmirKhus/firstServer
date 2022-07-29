package example.selenide;

import example.selenium.ConfProperties;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



public class LoginTest {

    @BeforeClass
    public static void setup() {
        open(ConfProperties.getProperty("loginpage"));
    }
    @Test
//    @Step
    public void  Login(){
        $(By.xpath("//*[@id=\"top-panel\"]/div[1]/div[1]/a")).click();
        $(By.xpath("//*[@id=\"eu_enter\"]/input[1]")).setValue(ConfProperties.getProperty("login")).click();
        $(By.xpath("//*[@id=\"eu_enter\"]/input[2]")).setValue(ConfProperties.getProperty("password")).click();
        $(By.xpath("//*[@id=\"eu_enter\"]/input[3]")).click();
        $(By.xpath("//*[@id=\"main-blocks\"]/div[1]/div[6]/a")).click();
        $(By.xpath("//*[@id=\"under-slider-menu\"]/div/a[1]/i")).click();
    }
}
