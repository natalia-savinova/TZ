package model;

import base.BaseModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordRecoveryPage extends BaseModel {

    @FindBy(xpath = "//div[@class = 'info-title']")
    private WebElement infoTitle;

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    public String infoTitleText() {
        return infoTitle.getText();
    }
}
