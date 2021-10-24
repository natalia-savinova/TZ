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
        new LoginPage(getDriver())
                .logoClick();

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

        Assert.assertEquals(loginPage.submitButtonText(), "Войти");
    }

    @Test
    public void testAuthorizationButtonPositive() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testAuthorizationEnterPositive() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD + "\n");

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testOpenLinkInNewWindow() {
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
    public void testForgerPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.gotoPasswordRecoveryPage();
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(getDriver());

        Assert.assertEquals(passwordRecoveryPage.infoTitleText(), "Восстановление пароля");
    }

    @Test
    public void testSwapLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(PASSWORD)
                .fillPassword(LOGIN)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testWrongLogin() {
        new LoginPage(getDriver())
                .fillLogin(WRONG_LOGIN)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testWrongPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(WRONG_PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testWrongLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(WRONG_LOGIN)
                .fillPassword(WRONG_PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testOnlyLogin() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации.");
    }

    @Test
    public void testOnlyPassword() {
        new LoginPage(getDriver())
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации.");
    }

    @Test
    public void testNoLoginNoPassword() {
        new LoginPage(getDriver())
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации.");
    }

    @Test
    public void testCapitalizationLogin() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN_TO_UPPERCASE)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testCapitalizationPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD_TO_LOWERCASE)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testCapitalizationLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN_TO_UPPERCASE)
                .fillPassword(PASSWORD_TO_LOWERCASE)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testSpaceBeforeLogin() {
        new LoginPage(getDriver())
                .fillLogin(" " + LOGIN)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testSpaceBeforePassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(" " + PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testSpaceBeforeLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(" " + LOGIN)
                .fillPassword(" " + PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testSpaceAfterLogin() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN  + " ")
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testSpaceAfterPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD  + " ")
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testSpaceAfterLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN  + " ")
                .fillPassword(PASSWORD  + " ")
                .submitButtonClick();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@class = 'avatar-full-name']")).getText(), USER_NAME);
    }

    @Test
    public void testShowPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD)
                .showPassword();

        Assert.assertTrue(getDriver().findElements(By.xpath("//input[@name = 'password' and @type = 'text']")).size() > 0);
    }

    @Test
    public void testLongLogin() {
        new LoginPage(getDriver())
                .fillLogin(LONG_STRING)
                .fillPassword(PASSWORD)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Неверные данные для авторизации");
    }

    @Test
    public void testLongPassword() {
        new LoginPage(getDriver())
                .fillLogin(LOGIN)
                .fillPassword(LONG_STRING)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Логин или пароль слишком длинные");
    }

    @Test
    public void testLongLoginAndPassword() {
        new LoginPage(getDriver())
                .fillLogin(LONG_STRING)
                .fillPassword(LONG_STRING)
                .submitButtonClick();

        Assert.assertEquals(getDriver().switchTo().alert().getText(), "Логин или пароль слишком длинные");
    }
}
