import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void testLogoLink() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//div[@class = 'head']/a")).click();

        WebElement result = getDriver().findElement(By.xpath("//div[@class = 'info-title']/div"));

        Assert.assertEquals(result.getText(), "Вход в систему");
    }

    @Test
    public void testRightSideLoginPage() {
        getDriver().get(URL);

        WebElement result1 = getDriver().findElement(By.
                xpath("//div[@class = 'mira-page-login-right-side-content-title']"));
        WebElement result2 = getDriver().findElement(By.
                xpath("//div[@class = 'mira-page-login-right-side-content-user-info']"));
        WebElement result3 = getDriver().findElement(By.
                xpath("//li[@class = 'mira-page-login-right-side-content-support-info-item']/p/span"));

        Assert.assertEquals(result1.getText(), "Добро пожаловать\n" +
                "в систему дистанционного обучения\n" +
                "Mirapolis LMS!");
        Assert.assertEquals(result2.getText(), "Обучайте, контролируйте результаты, мотивируйте сотрудников, используя лучшие практики лидеров рынка.");
        Assert.assertEquals(result3.getText(), "По всем вопросам обращайтесь к вашему менеджеру");
    }

    @Test
    public void testAuthorizationButtonPositive() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        WebElement result = getDriver().findElement(By.xpath("//div[@title= '" + USER_NAME + "']"));

        Assert.assertEquals(result.getText(), USER_NAME);
    }

    @Test
    public void testAuthorizationEnterPositive() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD + "\n");

        WebElement result = getDriver().findElement(By.xpath("//div[@title = 'Новости']"));

        Assert.assertEquals(result.getText(), "Новости");
    }

    @Test
    public void testForgerPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//a[@class = 'mira-default-login-page-link']/div")).click();

        WebElement result = getDriver().findElement(By.xpath("//div[@class = 'info-title']"));

        Assert.assertEquals(result.getText(), "Восстановление пароля");
    }

    @Test
    public void testSwapLoginAndPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(LOGIN);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Неверные данные для авторизации");
    }

    @Test
    public void testWrongLogin() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(WRONG_LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Неверные данные для авторизации");
    }

    @Test
    public void testWrongPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(WRONG_PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Неверные данные для авторизации");
    }

    @Test
    public void testWrongLoginAndPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(WRONG_LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(WRONG_PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Неверные данные для авторизации");
    }

    @Test
    public void testOnlyLogin() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Неверные данные для авторизации.");
    }

    @Test
    public void testOnlyPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Неверные данные для авторизации.");
    }

    @Test
    public void testNoLoginNoPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(WRONG_LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(WRONG_PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Неверные данные для авторизации");
    }

    @Test
    public void testCapitalizationLogin() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN_TO_UPPERCASE);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        WebElement result = getDriver().findElement(By.xpath("//div[@title= '" + USER_NAME + "']"));

        Assert.assertEquals(result.getText(), USER_NAME);
    }

    @Test
    public void testCapitalizationPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD_TO_LOWERCASE);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Неверные данные для авторизации");
    }

    @Test
    public void testCapitalizationLoginAndPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN_TO_UPPERCASE);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD_TO_LOWERCASE);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Неверные данные для авторизации");
    }

    @Test
    public void testSpaceBeforeLogin() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(" " + LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        WebElement result = getDriver().findElement(By.xpath("//div[@title= '" + USER_NAME + "']"));

        Assert.assertEquals(result.getText(), USER_NAME);
    }

    @Test
    public void testSpaceBeforePassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(" " + PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        WebElement result = getDriver().findElement(By.xpath("//div[@title= '" + USER_NAME + "']"));

        Assert.assertEquals(result.getText(), USER_NAME);
    }

    @Test
    public void testSpaceBeforeLoginAndPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(" " + LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(" " + PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        WebElement result = getDriver().findElement(By.xpath("//div[@title= '" + USER_NAME + "']"));

        Assert.assertEquals(result.getText(), USER_NAME);
    }

    @Test
    public void testSpaceAfterLogin() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN + " ");
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        WebElement result = getDriver().findElement(By.xpath("//div[@title= '" + USER_NAME + "']"));

        Assert.assertEquals(result.getText(), USER_NAME);
    }

    @Test
    public void testSpaceAfterPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD + " ");
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        WebElement result = getDriver().findElement(By.xpath("//div[@title= '" + USER_NAME + "']"));

        Assert.assertEquals(result.getText(), USER_NAME);
    }

    @Test
    public void testSpaceAfterLoginAndPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN + " ");
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD + " ");
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        WebElement result = getDriver().findElement(By.xpath("//div[@title= '" + USER_NAME + "']"));

        Assert.assertEquals(result.getText(), USER_NAME);
    }

    @Test
    public void testShowPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD);
        getDriver().findElement(By.id("show_password")).click();

        boolean isPresent = getDriver().findElements(By.xpath("//input[@name = 'password' and @type = 'text']")).size() > 0;

        Assert.assertTrue(isPresent);
    }

    @Test
    public void testLongLogin() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LONG_STRING);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(PASSWORD);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Неверные данные для авторизации");
    }

    @Test
    public void testLongPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LOGIN);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(LONG_STRING);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Логин или пароль слишком длинные");
    }

    @Test
    public void testLongLoginAndPassword() {
        getDriver().get(URL);
        getDriver().findElement(By.xpath("//input[@name = 'user']")).sendKeys(LONG_STRING);
        getDriver().findElement(By.xpath("//input[@name = 'password']")).sendKeys(LONG_STRING);
        getDriver().findElement(By.xpath("//button[@id = 'button_submit_login_form']")).click();

        String result = getDriver().switchTo().alert().getText();

        Assert.assertEquals(result, "Логин или пароль слишком длинные");
    }
}
