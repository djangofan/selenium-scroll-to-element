package test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GradleDocPage
{
    public static final String GRADLE_DOC_PAGE_URL = "https://docs.gradle.org/current/userguide/userguide.html";
    public static final By SECTION33 = By.xpath(".//dt/text()[starts-with(.,'33.7')]/..");
    public static final By ABOUT_GRADLE = By.partialLinkText("About Gradle");
    public static final By WRITING_BUILD_SCRIPTS = By.partialLinkText("Writing Gradle build scripts");

    public WebDriver getDriver()
    {
        return driver;
    }

    private WebDriver driver;

    public GradleDocPage(WebDriver driver)
    {
        this.driver = driver;
        navigateToDocPage();
    }

    public void navigateToDocPage()
    {
        driver.manage().window().setPosition(new Point(50,20));
        driver.manage().window().setSize(new Dimension(900, 1000));
        driver.get(GRADLE_DOC_PAGE_URL);
    }

    public Long getViewPortHeight(WebDriver driver)
    {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        return (Long) je.executeScript("return window.innerHeight;");
    }

    public List<String> getBoundedRectangleOfElement(WebElement we)
    {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        List<String> bounds = (ArrayList<String>) je.executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return [ '' + parseInt(rect.left), '' + parseInt(rect.top), '' + parseInt(rect.width), '' + parseInt(rect.height) ]", we);
        System.out.println("left: " + bounds.get(0));
        System.out.println("top: " + bounds.get(1));
        System.out.println("width: " + bounds.get(2));
        System.out.println("height: " + bounds.get(3));
        return bounds;
    }

    public void scrollToElementAndCenterVertically(WebElement we)
    {
        List<String> bounds = getBoundedRectangleOfElement(we);
        Long totalInnerPageHeight = getViewPortHeight(driver);
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollTo(0, " + (Integer.parseInt(bounds.get(1)) - (totalInnerPageHeight/2)) + ");");
        outlineElement(we);
    }

    public void outlineElement(WebElement we)
    {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].style.outline = \"thick solid #0000FF\";", we);
    }

    public void closeDriver()
    {
        waitFiveSeconds();
        driver.close();
    }

    public WebElement findElement(By locator)
    {
        return driver.findElement(locator);
    }

    public void waitFiveSeconds()
    {
        try {
            Sleeper.SYSTEM_SLEEPER.sleep(new Duration(5, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
