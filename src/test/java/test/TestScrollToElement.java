package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestScrollToElement
{
    private WebDriver driver;
    private GradleDocPage gradleDocPage;

    @Before
    public void setUpDriver()
    {
        this.driver = new FirefoxDriver();
        gradleDocPage = new GradleDocPage(driver);
    }

    @Test
    public void testScrollToElement()
    {
        WebElement element1 = gradleDocPage.findElement(gradleDocPage.SECTION33);

        gradleDocPage.scrollToElementAndCenterVertically(element1);

        System.out.println(element1.getText());
        gradleDocPage.waitFiveSeconds();

        WebElement element2 = gradleDocPage.findElement(gradleDocPage.ABOUT_GRADLE);

        gradleDocPage.scrollToElementAndCenterVertically(element2);

        System.out.println(element2.getText());
        gradleDocPage.waitFiveSeconds();

        WebElement element3 = gradleDocPage.findElement(gradleDocPage.WRITING_BUILD_SCRIPTS);

        gradleDocPage.scrollToElementAndCenterVertically(element3);

        System.out.println(element3.getText());
    }

    @After
    public void shutdownDriver()
    {
        gradleDocPage.closeDriver();
    }

}
