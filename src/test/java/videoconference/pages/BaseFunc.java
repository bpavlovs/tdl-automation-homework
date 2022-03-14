package videoconference.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BaseFunc {
    private WebDriver browser;
    private WebDriverWait wait;

    public BaseFunc() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");

        browser = new ChromeDriver(options);
        browser.manage().window().maximize();
        wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    }

    public WebElement findElement(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return browser.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return browser.findElements(locator);
    }

    public void openUrl(String url) {
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "http://" + url;
        }
        browser.get(url);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void type(By locator, String text) {
        WebElement inputField = findElement(locator);
        inputField.clear();
        inputField.sendKeys(text);
    }

    public BufferedImage getActualImage(WebElement target) {
        return new AShot().takeScreenshot(browser, target).getImage();
    }

    public BufferedImage getExpectedImage(String imgName) throws IOException {
        return ImageIO.read(new File("src/test/resources/img/" + imgName + ".png"));
    }

    public Boolean compareImages(BufferedImage actualImage, BufferedImage expectedImage) {
        ImageDiffer imageDiffer = new ImageDiffer();
        return imageDiffer.makeDiff(expectedImage, actualImage).hasDiff();
    }

    public void closeBrowser() {
        browser.close();
    }
}
