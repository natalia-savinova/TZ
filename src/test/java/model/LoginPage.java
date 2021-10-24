package model;

import base.BaseModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseModel {

    @FindBy(xpath = "//div[@class = 'head']/a")
    private WebElement logo;

    @FindBy(xpath = "//input[@name = 'user']")
    private WebElement login;

    @FindBy(xpath = "//input[@name = 'password']")
    private WebElement password;

    @FindBy(xpath = "//button[@id = 'button_submit_login_form']")
    private WebElement submitButton;

    @FindBy(id = "show_password")
    private WebElement showPassword;

    @FindBy(xpath = "//a[@class = 'mira-default-login-page-link']/div")
    private WebElement forgetPassword;

    @FindBy(xpath = "//input[@name = 'user' and @placeholder = 'Введите ваш логин']")
    private WebElement loginPlaceholder;

    @FindBy(xpath = "//input[@name = 'password' and @placeholder = 'Введите ваш пароль']")
    private WebElement passwordPlaceholder;

    @FindBy(xpath = "//div[@class = 'mira-page-login-right-side-content-title']")
    private WebElement rightSideTitle;

    @FindBy(xpath = "//div[@class = 'mira-page-login-right-side-content-user-info']")
    private WebElement rightSideInfo;

    @FindBy(xpath = "//li[@class = 'mira-page-login-right-side-content-support-info-item']/p/span")
    private WebElement rightSideSupportInfo;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void logoClick() {
        logo.click();
    }

    public LoginPage fillLogin(String value) {
        login.sendKeys(value);
        return this;
    }

    public LoginPage fillPassword(String value) {
        password.sendKeys(value);
        return this;
    }

    public PersonalAreaPage submitButtonClick() {
        submitButton.click();
        return new PersonalAreaPage(getDriver());
    }

    public boolean loginPlaceholderIsPresent() {
        return loginPlaceholder.isEnabled();
    }

    public boolean passwordPlaceholderIsPresent() {
        return passwordPlaceholder.isEnabled();
    }

    public String submitButtonText() {
        return submitButton.getText();
    }

    public void showPassword() {
        showPassword.click();
    }

    public String rightSideTitleText() {
        return rightSideTitle.getText();
    }

    public String rightSideInfoText() {
        return rightSideInfo.getText();
    }

    public String rightSideSupportInfoText() {
        return rightSideSupportInfo.getText();
    }

    public PasswordRecoveryPage gotoPasswordRecoveryPage() {
        forgetPassword.click();
        return new PasswordRecoveryPage(getDriver());
    }
}
