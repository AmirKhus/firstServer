package example.cucumber.step;

import example.selenium.LoginTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Dowlaud {
    LoginTest loginTest = new LoginTest();
    @Given("{string} portfolio")
    public void underSliderMenuPortfolio(String arg0) {
        $(byXpath(arg0)).click();
        $(byXpath("//*[@id=\"info\"]/div/div[1]/div[1]/div[2]/div[2]")).click();
        $(byXpath("//*[@id=\"answer\"]/a[1]")).click();
    }

    @When("delete image and loud")
    public void deleteImageAndLoudUnderSliderMenu(String arg0, String arg1) {
        $(byXpath("//*[@id=\"under-slider-menu\"]/div/a[2]")).click();
        $(byXpath("//*[@id=\"info\"]/div/div[1]/div[1]/div[2]/div[2]")).click();
        $(byXpath("//*[@id=\"answer\"]/a[1]")).click();
    }

}
