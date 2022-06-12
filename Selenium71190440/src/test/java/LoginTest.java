import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class LoginTest {
    ChromeDriver driver;
    @Given("browser dibuka")
    public void browser_dibuka() {
        System.out.println("Inside Step - Buka Browser");
        System.setProperty("webdriver.chrome.driver",
                Objects.requireNonNull(getClass().getClassLoader().getResource("chromeDriver/chromedriver.exe")).getFile());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }

    @Given("pengguna dalam hlm register")
    public void pengguna_dalam_hlm_register() {
        System.out.println("Inside Step - user ada di halaman register");
        driver.navigate().to("https://demo.guru99.com/insurance/v1/register.php");
    }
    @Given("pengguna ke hlm login")
    public void pengguna_ke_hlm_login() {
        System.out.println("Inside step - user ke halaman login");
        driver.findElement(By.xpath("/html/body/div[3]/a")).click();
    }

    @When("^pengguna masukan (.*) and (.*)$")
    public void pengguna_masukan_namaakun_and_sandi(String namaakun, String sandi) {
        System.out.println("Inside Step - Ada di fungsi pengguna memasukkan nama akun dan sandi");
        driver.findElement(By.name("email")).sendKeys(namaakun);
        driver.findElement(By.name("password")).sendKeys(sandi);
        Assertions.assertTrue(namaakun.length()!=0 && sandi.matches("[a-zA-Z0-9]{8,13}"), "Tidak boleh kosong");
    }

    @When("tombol login diklik")
    public void tombol_login_diklik() {
        System.out.println("Inside Step - Ada di fungsi klik tombol login");
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[3]/input")).click();
    }
    @Then("pengguna redirect ke hlm utama")
    public void pengguna_redirect_ke_hlm_utama() {
        System.out.println("Inside Step - Ada di halaman halaman utama");
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[3]/form/input"));
        Assertions.assertTrue(list.size()>0, "Text not found");
    }
}
