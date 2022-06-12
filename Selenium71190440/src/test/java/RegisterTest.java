import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegisterTest {
    ChromeDriver driver;
    String username_g;
    String password_g;

    @Given("browser opened")
    public void browser_opened() {
        System.out.println("Inside Step - Buka Browser");
        System.setProperty("webdriver.chrome.driver",
                Objects.requireNonNull(getClass().getClassLoader().getResource("chromeDriver/chromedriver.exe")).getFile());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }

    @Given("user in register page")
    public void user_in_register_page() {
        System.out.println("Inside Step - user ada di halaman register");
        driver.navigate().to("https://demo.guru99.com/insurance/v1/register.php");
        List<WebElement> btninputan = driver.findElements(By.tagName("input"));
        List button2 = new ArrayList();
        for (WebElement element: btninputan) {
            if (element.getAttribute("value").equals("Reset")){
                button2.add(element.getText());
            }
            if (element.getAttribute("value").equals("Create")){
                button2.add(element.getText());
            }
        }
        Assertions.assertTrue(button2.size()>1, "Button create dan atau reset tidak ada");
    }

    @When("^user insert (.*) and (.*)$")
    public void user_insert_username_and_password(String username, String password) {
        System.out.println("Inside Step - Ada di fungsi pengguna memasukkan nama akun dan sandi");
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("c_password")).sendKeys(password);
        username_g = username;
        password_g = password;
    }

    @When("register button clicked")
    public void register_button_clicked() {
        System.out.println("Inside Step - Ada di fungsi klik tombol login");
        driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[5]/input[2]")).click();
        Assertions.assertTrue(username_g.length()!=0 && password_g.matches("[a-zA-Z0-9]{8,13}"), "Jumlah karakter harus antara 8-13 dan alphanumeric");
    }

    @Then("user redirect to login screen")
    public void user_redirect_to_login_screen() {
        System.out.println("Inside Step - Ada di halaman register diarahkan ke halaman login");
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"login-form\"]/div[3]/input"));
        Assertions.assertTrue(list.size()>0, "Text not found");
        driver.close();
        driver.quit();
    }
}
