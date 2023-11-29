package testng.tests;

import org.openqa.selenium.WebDriver;
import testng.services.SwagLabsOperations;

public class LogInAndLogOut {
    public LogInAndLogOut (WebDriver driver){
        swagOps = new SwagLabsOperations(driver);
    }
    private final SwagLabsOperations swagOps;

    public void inAndOut(){
        swagOps.setUpDriver();
        swagOps.login();
        swagOps.logOut();
    }
}
