package example.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[@id=\"eu_enter\"]/input[1]")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id=\"eu_enter\"]/input[3]")
    private WebElement loginknopka;

    @FindBy(xpath = "//*[@id=\"eu_enter\"]/input[3]")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"top-panel\"]/div[1]/div[1]/a")
    private WebElement clickVhodBtn;

    @FindBy(xpath = "//*[@id=\"eu_enter\"]/input[2]")
    private WebElement passwdField;


    public void inputLogin(String login) {
        loginField.sendKeys(login); }
    /**
     * метод для ввода пароля
     */
    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd); }
    /**
     * метод для осуществления нажатия кнопки входа в аккаунт
     */
    public void clickLoginBtn() {
        loginBtn.click(); }

    public void clickVhodBtn() {
        clickVhodBtn.click(); }

}
