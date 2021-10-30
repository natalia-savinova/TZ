import base.BaseTest;
import model.LoginPage;
import model.PasswordRecoveryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LoginPageTest extends BaseTest {

    @Test
    public void goLogoLink() {
        new LoginPage(getDriver())
                .logoClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'info-title']/div")).getText(), "Вход в систему");
    }

    @Test
    public void checkRightSideLoginPage() {
        LoginPage loginPage = new LoginPage(getDriver());

        Assert.assertEquals(loginPage.rightSideTitleText(), "Добро пожаловать\n" +
                "в систему дистанционного обучения\n" +
                "Mirapolis LMS!");
        Assert.assertEquals(loginPage.rightSideInfoText(), "Обучайте, контролируйте результаты, мотивируйте сотрудников, используя лучшие практики лидеров рынка.");
        Assert.assertEquals(loginPage.rightSideSupportInfoText(), "По всем вопросам обращайтесь к вашему менеджеру");
    }


    @Test
    public void checkPlaceholderLogin() {
        LoginPage loginPage = new LoginPage(getDriver());

        Assert.assertTrue(loginPage.loginPlaceholderIsPresent());
    }

    @Test
    public void checkPlaceholderPassword() {
        LoginPage loginPage = new LoginPage(getDriver());

        Assert.assertTrue(loginPage.passwordPlaceholderIsPresent());
    }

    @Test
    public void checkButtonText() {
        LoginPage loginPage = new LoginPage(getDriver());

        Assert.assertEquals(loginPage.submitButtonText(), "Войти");
    }

    @Test
    public void authorizationButtonPositive() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void authorizationEnterPositive() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD + "\n");

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void openLinkInNewWindow() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        String currentUrl = getDriver().getCurrentUrl();

        ((JavascriptExecutor)getDriver()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        getDriver().get(currentUrl);

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void goForgerPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.gotoPasswordRecoveryPage();
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(getDriver());

        Assert.assertEquals(passwordRecoveryPage.infoTitleText(), "Восстановление пароля");
    }

    @Test
    public void swapLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(PASSWORD)
                .fillPassword(LOGIN)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void enterWrongLogin() {
        new LoginPage(getDriver())
                .fillLogin(WRONG_LOGIN)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void enterWrongPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(WRONG_PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void enterWrongLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(WRONG_LOGIN)
                .fillPassword(WRONG_PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void enterOnlyLogin() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации.");
    }

    @Test
    public void enterOnlyPassword() {
        new LoginPage(getDriver())
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации.");
    }

    @Test
    public void enterNoLoginNoPassword() {
        new LoginPage(getDriver())
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации.");
    }

    @Test
    public void enterCapitalizationLogin() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN_TO_UPPERCASE)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void enterCapitalizationPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD_TO_LOWERCASE)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void enterCapitalizationLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN_TO_UPPERCASE)
                .fillPassword(PASSWORD_TO_LOWERCASE)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void addSpaceBeforeLogin() {
        new LoginPage(getDriver())
                .fillLogin(" " + LOGIN)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void addSpaceBeforePassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(" " + PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void addSpaceBeforeLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(" " + LOGIN)
                .fillPassword(" " + PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void addSpaceAfterLogin() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN  + " ")
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void addSpaceAfterPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD  + " ")
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void addSpaceAfterLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN  + " ")
                .fillPassword(PASSWORD  + " ")
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void clickShowPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD)
                .showPassword();

        Assert.assertTrue(getDriver().findElements(By.xpath("//input[@name = 'password' and @type = 'text']")).size() > 0);
    }

    @Test
    public void enterLongLogin() {
        new LoginPage(getDriver())
                .fillLogin(LONG_STRING)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void enterLongPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(LONG_STRING)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Логин или пароль слишком длинные");
    }

    @Test
    public void enterLongLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(LONG_STRING)
                .fillPassword(LONG_STRING)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Логин или пароль слишком длинные");
    }
}
