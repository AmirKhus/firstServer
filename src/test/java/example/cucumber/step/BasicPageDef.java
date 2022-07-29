package example.cucumber.step;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BasicPageDef {

    @Given("i am on {string}")
    public void iAmOn(String arg0) {
        open(arg0);
    }

    @When("i open {string} page")
    public void iOpenTopPanelPage(String arg0) {
        $(By.xpath(arg0)).click();
    }

    @And("type text: {string} to input with name {string}")
    public void typeTextEu_enterToInputWithName(String arg0, String arg1) {
        $(By.xpath(arg0)).setValue(arg1).click();

    }

    @And("type text: {string} to input with password {string}")
    public void typeTextEu_enterToInputWithPassword(String arg0, String arg1) {
        $(By.xpath(arg0)).setValue(arg1).click();

    }


    @And("press button with id {string}")
    public void pressButtonWithId(String arg0) {
        $(By.xpath(arg0)).click();
    }

    @And("{string} page should be opened")
    public void pageShouldBeOpened(String arg0) {
        $(By.xpath(arg0)).click();
    }

    @And("I visit {string} page")
    public void iVisitPage(String arg0) {
        $(By.xpath(arg0)).click();
//        $(By.xpath("//*[@id=\"slider\"]/div/h1")).shouldHave(text(arg0));

    }

    @And("I visit {string} portfolio")
    public void iVisitPortfolio(String arg0) {
        $(By.xpath(arg0)).click();
//        $(By.xpath("//*[@id=\"slider\"]/div/h1")).shouldHave(text(arg0));
    }

    @Then("I should see the find {string} on the page")
    public void iShouldSeeTheFindOnThePage(String arg0) {
        $(By.xpath("//*[@id=\"info\"]/div/div[1]/div[2]/div[2]/span[2]")).shouldHave(text(arg0));
    }

    public void restAPI() {
//        WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
    }



//    @Before
//    public void openUrs() {
//        open(ConfProperties.getProperty("loginpage"));
//    }
}
