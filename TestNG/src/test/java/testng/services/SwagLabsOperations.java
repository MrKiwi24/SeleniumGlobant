package testng.services;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SwagLabsOperations {
    private final FluentWaitForElement wait = new FluentWaitForElement();
    private final WebDriver driver;
    private final SoftAssert softAssert = new SoftAssert();
    public SwagLabsOperations (WebDriver driver){
        this.driver = driver;
    }
    public void setUpDriver(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://www.saucedemo.com/");
    }
    public void login(){
        By logInBox = By.xpath("//div[@class='login-box']");
        wait.forElement(logInBox, driver);

        WebElement inputUsername = driver.findElement(By.xpath("//input[@id='user-name']"));
        inputUsername.sendKeys("standard_user");

        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inputPassword.sendKeys("secret_sauce");

        inputPassword.sendKeys(Keys.ENTER);

        By pageContent = By.xpath("//div[@id='contents_wrapper']");
        wait.forElement(pageContent, driver);


        softAssert.assertEquals(
                driver.findElement(By.xpath("//body/div[@id='root']/div[@id='page_wrapper']/div[@id='contents_wrapper']/div[2]")).isDisplayed(),
                true);
        softAssert.assertAll();
    }
    public void addRandomItemsAndSeeCart(int numberOfItems){
        List<WebElement> inventory = driver.findElements(By.cssSelector(".pricebar button:only-of-type"));

        softAssert.assertEquals(inventory.size(), 6);
        softAssert.assertEquals(findAndSelectRandomItems(inventory, numberOfItems).size(), numberOfItems);

        softAssert.assertAll();
        seeCart();
    }
    private void seeCart(){
        WebElement cartIcon = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cartIcon.click();

        By cartList = By.xpath("//div[@class='cart_list']");
        wait.forElement(cartList,driver);

    }
    public void emptyCart(){
        List<WebElement> cart = driver.findElements(By.cssSelector(".item_pricebar button:only-of-type"));

        for (WebElement webElement : cart) {
            webElement.click();
        }
        softAssert.assertEquals(driver.findElements(By.xpath("//div[@class='cart_item']")).size(), 0);
        softAssert.assertAll();
    }
    private List<WebElement> findAndSelectRandomItems(List<WebElement> inventory, int picks){
        List<WebElement> items = new ArrayList<>();

        Random rand = new Random();

        for (int i = 0; i < picks; i++){
            WebElement pick = inventory.get(rand.nextInt(inventory.size()));
            items.add(pick);
            inventory.remove(pick);
            pick.click();
        }

        return items;
    }
    public void checkout(){
        WebElement checkoutButton = driver.findElement(By.xpath("//button[@id='checkout']"));
        checkoutButton.click();
        fillInPersonalDetails();
        finishPurchase();
    }
    private void fillInPersonalDetails(){
        By personalInfoForm = By.xpath("//div[@id='contents_wrapper']");
        wait.forElement(personalInfoForm, driver);

        WebElement firstName = driver.findElement(By.xpath("//input[@id='first-name']"));
        WebElement lastName = driver.findElement(By.xpath("//input[@id='last-name']"));
        WebElement zipCode = driver.findElement(By.xpath("//input[@id='postal-code']"));
        WebElement continueButton = driver.findElement(By.xpath("//input[@id='continue']"));

        firstName.sendKeys("Firefox");
        lastName.sendKeys("Mozilla");
        zipCode.sendKeys("15948");
        continueButton.click();
    }
    private void finishPurchase(){
        By summary = By.xpath("//div[@class='summary_info']");
        wait.forElement(summary, driver);

        WebElement finishButton = driver.findElement(By.xpath("//button[@id='finish']"));
        finishButton.click();
        String okMessage = driver.findElement(By.cssSelector(".complete-header")).getText();
        softAssert.assertEquals(okMessage, "Thank you for your order!");

        softAssert.assertAll();
    }
    public void logOut(){
        By pageContents = By.xpath("//div[@id='contents_wrapper']");
        wait.forElement(pageContents, driver);

        WebElement burgerMenuButton = driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']"));
        burgerMenuButton.click();

        WebElement logOutButton = driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
        logOutButton.click();

        pageContents = By.xpath("//div[@class='login_container']");
        wait.forElement(pageContents, driver);

        WebElement logInBox = driver.findElement(By.xpath("//div[@class='login-box']"));
        softAssert.assertEquals(logInBox.isDisplayed(), true);
        softAssert.assertAll();
    }

}
