package testng.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class test {
    //public WebDriver driver = new FirefoxDriver();
    public WebDriver driver = new ChromeDriver();
    @Test
    public void completePurchaseTest(){
        CompletePurchase test = new CompletePurchase(driver);
        test.completePurchase();
    }
    @Test
    public void fillAndEmptyCart(){
        FillAndEmptyShoppingCart test = new FillAndEmptyShoppingCart(driver);
        test.fillAndEmptyShoppingCart();
    }
    @Test
    public void inAndOut(){

    }
    @AfterTest
    public void quit(){
        driver.quit();
    }
}
