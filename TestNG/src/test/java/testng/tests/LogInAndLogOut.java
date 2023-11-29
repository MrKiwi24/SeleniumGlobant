package testng.tests;

import org.openqa.selenium.WebDriver;
import testng.services.SwagLabsOperations;

public class LogInAndLogOut {
    private final WebDriver driver;
    public LogInAndLogOut (WebDriver driver){
        this.driver = driver;
        swagOps = new SwagLabsOperations(driver);
    }
    private SwagLabsOperations swagOps;

    public void inAndOut(){
        swagOps.setUpDriver();
        swagOps.login();
        swagOps.logOut();
    }
}
