package pages;

import lib.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;

/**
 * Created by DadaL1fe on 20.05.2016.
 */
public abstract class AnyPage {
    public AnyPage() throws FileNotFoundException {
        PageFactory.initElements(Init.getDriver(), this); // инициализируем элементы
        waitPageToLoad();
    }

    public void waitPageToLoad() throws FileNotFoundException {
        new WebDriverWait(Init.getDriver(), 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public void click(WebElement element) throws FileNotFoundException {
        new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions.elementToBeClickable(element));
        System.out.println("Click to " + element.getText());
        element.click();
    }

    public void click(By by) throws FileNotFoundException {
        WebElement element = Init.getDriver().findElement(by);
        click(element);
    }

    public void setText(WebElement element, String text) throws FileNotFoundException {
        element.clear();
        element.sendKeys(text);
    }

    public void setText(By by, String text) throws FileNotFoundException {
        WebElement element = Init.getDriver().findElement(by);
        setText(element, text);
    }

    public void selectByText(Select select, String text) throws FileNotFoundException {
        select.selectByVisibleText(text);
    }
}
