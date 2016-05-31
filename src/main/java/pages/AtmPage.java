package pages;

import lib.Init;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DadaL1fe on 23.05.2016.
 */
public class AtmPage extends AnyPage {
    @FindBys(@FindBy(xpath = "//ul[@id='branchListItems']/li"))
    private List<WebElement> elems_nearest;

    @FindBys(@FindBy(xpath = "//div[@class='branch-list-item-block']//p[1]"))
    private List<WebElement> elems_locations;

    @FindBy(xpath = "//label[text()='Платёжные устройства']")
    private WebElement elem_checkPayment;

    @FindBy(xpath = "//button[@class='sbf_button show-more']")
    private WebElement btn_showMore;

    @FindBy(xpath = "//label[text()='Отделения']")
    private WebElement elem_checkOffice;

    private static int countLocations;
    public AtmPage() throws IOException {
        new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//h1[text()='Отделения и банкоматы']")));
    }

    public void checkGreenIcons() throws FileNotFoundException, InterruptedException {
        Thread.sleep(10000);
        for (WebElement element : elems_nearest) {
            Assert.assertEquals("item-list-icon filial", element.findElement(By.tagName("span")).getAttribute("class"));
        }
    }

    public void checkMinLocation() {
        Assert.assertTrue("ERROR, size <1!", elems_nearest.size() >= 1);
    }

    public void checkOrderLocations() {
        Pattern patDist = Pattern.compile("[0-9]+(.[0-9]+)?");
        ArrayList<Double> listDistance = new ArrayList<>();
        Pattern patName = Pattern.compile("[к]?[м]{1}");
        ArrayList<String> nameDistance = new ArrayList<>();
        for (WebElement element : elems_locations) {
            Matcher matcher = patDist.matcher(element.getText());
            Matcher matcherName = patName.matcher(element.getText());
            while (matcher.find()) {
                listDistance.add((Double.parseDouble(matcher.group())));
            }
            while (matcherName.find()) {
                nameDistance.add(matcherName.group());
            }
        }
        for (int i = 0; i < listDistance.size(); i++) {
            if (nameDistance.get(i).equals("км")) {
                double number = listDistance.get(i) * 1000;
                listDistance.remove(i);
                listDistance.add(i, number);
            }
        }
        for (int i = 0; i < listDistance.size() - 1; i++) {
            Assert.assertTrue(listDistance.get(i) <= listDistance.get(i + 1));
        }
        countLocations = listDistance.size();
    }
    public void choosePaymentDevice() throws FileNotFoundException, InterruptedException {
        click(elem_checkPayment);
        Thread.sleep(5000);
        int countPayment = 0;
        for(WebElement element : elems_nearest){
            if (element.findElement(By.tagName("span")).getAttribute("class").equals("item-list-icon itt"))
            countPayment++;
        }
        Assert.assertTrue(countPayment>=1);
    }
    public void pushShowMore() throws FileNotFoundException, InterruptedException {
        click(btn_showMore);
        Thread.sleep(5000);
        Assert.assertTrue(elems_locations.size()>countLocations);
        checkOrderLocations();
    }
    public void uncheckOffice() throws FileNotFoundException, InterruptedException {
        click(elem_checkOffice);
        Thread.sleep(5000);
        int countPayment = 0;
        for(WebElement element : elems_nearest){
            if (element.findElement(By.tagName("span")).getAttribute("class").equals("item-list-icon itt"))
                countPayment++;
        }
        Assert.assertEquals(elems_locations.size(), countPayment);
    }

}
