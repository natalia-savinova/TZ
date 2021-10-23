package model;

import base.BaseModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalAreaPage extends BaseModel {
    @FindBy(xpath = "//div[@class = 'avatar-full-name']")
    private WebElement avatarFullName;

    public PersonalAreaPage(WebDriver driver) {
        super(driver);
    }
}
