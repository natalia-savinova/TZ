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
    public void testLogoLink() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.logoClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'info-title']/div")).getText(), "Вход в систему");
    }

    @Test
    public void testRightSideLoginPage() {
        LoginPage loginPage = new LoginPage(getDriver());

        Assert.assertEquals(loginPage.rightSideTitleText(), "Добро пожаловать\n" +
                "в систему дистанционного обучения\n" +
                "Mirapolis LMS!");
        Assert.assertEquals(loginPage.rightSideInfoText(), "Обучайте, контролируйте результаты, мотивируйте сотрудников, используя лучшие практики лидеров рынка.");
        Assert.assertEquals(loginPage.rightSideSupportInfoText(), "По всем вопросам обращайтесь к вашему менеджеру");
    }


    @Test
    public void testPlaceholderLogin() {
        LoginPage loginPage = new LoginPage(getDriver());

        Assert.assertTrue(loginPage.loginPlaceholderIsPresent());
    }

    @Test
    public void testPlaceholderPassword() {
        LoginPage loginPage = new LoginPage(getDriver());

        Assert.assertTrue(loginPage.passwordPlaceholderIsPresent());
    }

    @Test
    public void testButtonText() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.submitButtonText();

        Assert.assertEquals(loginPage.submitButtonText(), "Войти");
    }

    @Test
    public void testAuthorizationButtonPositive() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN);
        loginPage.fillPassword(PASSWORD);
        loginPage.submitButtonClick();
        //return?

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testAuthorizationEnterPositive() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN);
        loginPage.fillPassword(PASSWORD + "\n");
        //return?

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testOpenLinkInNewWindow() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN);
        loginPage.fillPassword(PASSWORD);
        loginPage.submitButtonClick();

        String currentUrl = getDriver().getCurrentUrl();

        ((JavascriptExecutor)getDriver()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        getDriver().get(currentUrl);

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testForgerPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.gotoPasswordRecoveryPage();
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(getDriver());

        Assert.assertEquals(passwordRecoveryPage.infoTitleText(), "Восстановление пароля");
    }

    @Test
    public void testSwapLoginAndPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(PASSWORD);
        loginPage.fillPassword(LOGIN);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testWrongLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(WRONG_LOGIN);
        loginPage.fillPassword(PASSWORD);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testWrongPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN);
        loginPage.fillPassword(WRONG_PASSWORD);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testWrongLoginAndPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(WRONG_LOGIN);
        loginPage.fillPassword(WRONG_PASSWORD);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testOnlyLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации.");
    }

    @Test
    public void testOnlyPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillPassword(PASSWORD);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации.");
    }

    @Test
    public void testNoLoginNoPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации.");
    }

    @Test
    public void testCapitalizationLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN_TO_UPPERCASE);
        loginPage.fillPassword(PASSWORD);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testCapitalizationPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN);
        loginPage.fillPassword(PASSWORD_TO_LOWERCASE);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testCapitalizationLoginAndPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN_TO_UPPERCASE);
        loginPage.fillPassword(PASSWORD_TO_LOWERCASE);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testSpaceBeforeLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(" " + LOGIN);
        loginPage.fillPassword(PASSWORD);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testSpaceBeforePassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN);
        loginPage.fillPassword(" " + PASSWORD);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testSpaceBeforeLoginAndPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(" " + LOGIN);
        loginPage.fillPassword(" " + PASSWORD);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testSpaceAfterLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN  + " ");
        loginPage.fillPassword(PASSWORD);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testSpaceAfterPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN);
        loginPage.fillPassword(PASSWORD  + " ");
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testSpaceAfterLoginAndPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN  + " ");
        loginPage.fillPassword(PASSWORD  + " ");
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testShowPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN);
        loginPage.fillPassword(PASSWORD);
        loginPage.showPassword();

        Assert.assertTrue(getDriver().findElements(By.xpath("//input[@name = 'password' and @type = 'text']")).size() > 0);
    }

    @Test
    public void testLongLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LONG_STRING);
        loginPage.fillPassword(PASSWORD);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testLongPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LOGIN);
        loginPage.fillPassword(LONG_STRING);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Логин или пароль слишком длинные");
    }

    @Test
    public void testLongLoginAndPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.fillLogin(LONG_STRING);
        loginPage.fillPassword(LONG_STRING);
        loginPage.submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Логин или пароль слишком длинные");
    }
}
