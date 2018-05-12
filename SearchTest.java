import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchTest {
    ChromeDriver chromeDriver;

    @Before
    public void setup(){
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    }

    @Test
    public void testSearcListResult(){
        accountLoginAs("test@aditus.info","123456a");
        searchItem("blackberry");
        List<WebElement> listResult = chromeDriver.findElementsByXPath("//li[@class='s-item']");
        Assert.assertEquals(50,listResult.size());
        exitAccountAndAssert();
    }

    public void exitAccountAndAssert() {
        WebElement accountPopUpButton = chromeDriver.findElementById("gh-ug");
        accountPopUpButton.click();
        WebElement exitAccountButton = chromeDriver.findElementByXPath("//a[@_sp='m570.l2622']");
        exitAccountButton.click();
        WebElement exitResultText = chromeDriver.findElementByClassName("ds3pHTxt");
        Assert.assertTrue(exitResultText.isEnabled());
    }

    public void searchItem(String searchItemName) {
        WebElement searchField = chromeDriver.findElementById("gh-ac");
        searchField.sendKeys(searchItemName);
        WebElement searchBtn = chromeDriver.findElementByXPath("//input[@id='gh-btn']");
        searchBtn.click();
    }


    public void accountLoginAs(String login, String password) {
        chromeDriver.get("https://www.ebay.com");
        WebElement goToLoginPageButton = chromeDriver.findElementByXPath("//a[@_sp='m570.l1524']");
        goToLoginPageButton.click();
        WebElement loginField = chromeDriver.findElementById("userid");
        loginField.sendKeys(login);
        WebElement passField = chromeDriver.findElementById("pass");
        passField.sendKeys(password);
        WebElement enterSystemButton = chromeDriver.findElementById("sgnBt");
        enterSystemButton.click();
    }

    @After
    public void closeBrowser(){ chromeDriver.close();
    }


}
