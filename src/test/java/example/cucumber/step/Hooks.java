package example.cucumber.step;

import example.selenium.ConfProperties;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.*;

public class Hooks {
    @Before
    public void openUrs() {
        open(ConfProperties.getProperty("loginpage"));
    }
}
