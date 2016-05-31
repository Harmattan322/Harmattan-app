package pages;

import lib.Init;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by DadaL1fe on 22.05.2016.
 */
public class TravelInsurancePage extends AnyPage {
    @FindBy(xpath = "//span[@class = 'ng-binding ng-scope b-dropdown-title']")
    private WebElement elem_region;

    @FindBy(xpath = "//*[@name='startDate']")
    private WebElement elem_startDate;

    @FindBy(xpath = "//*[@name='finishDate']")
    private WebElement elem_finishDate;

    @FindBy(xpath = "//span[@class='b-checkbox-field']/input")
    private WebElement elem_checkBoxYear;

    @FindBy(xpath = "//span[@class='b-text-field b-form-policy-duration']/input")
    private WebElement elem_policyDuration;

    @FindBy(xpath = "//input[@name='insuredCount60']")
    private WebElement elem_3_60Years;

    @FindBy(xpath = "//input[@name='insuredCount2']")
    private WebElement elem_0_2Years;

    @FindBy(xpath = "//input[@name='insuredCount70']")
    private WebElement elem_61_70Years;

    @FindBy(xpath = "//div[text()='Минимальная']/..")
    private WebElement elem_min;

    @FindBy(xpath = "//div[text()='Достаточная']/..")
    private WebElement elem_enought;

    @FindBy(xpath = "//div[text()='Максимальная']/..")
    private WebElement elem_max;

    @FindBy(xpath = "//span[text()='Спортивный']//..")
    private WebElement elem_sport;

    @FindBy(xpath = "//span[text()='Защита багажа']//..")
    private WebElement elem_baggage;

    @FindBy(xpath = "//span[text()='Особый случай']//..")
    private WebElement elem_special;

    @FindBy(xpath = "//span[text()='Личный адвокат']//..")
    private WebElement elem_advocate;

    @FindBy(xpath = "//span[text()='Предусмотрительный']//..")
    private WebElement elem_cautious;

    @FindBy(xpath = "//span[text()='Оформление']/../../..")
    private WebElement elem_reg;

    @FindBy(xpath = "//span[text()='Подтверждение']/../../..")
    private WebElement elem_confirm;

    @FindBy(xpath = "//div[@class='b-form-section-inside']/dl[2]/dd[1]/span[1]")
    private WebElement elem_total;

    public TravelInsurancePage() throws IOException {
        new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//h2[text() = 'Страхование путешественников']")));
    }
    public void checkPolisFields() throws FileNotFoundException, InterruptedException {
        Thread.sleep(3000);
        Assert.assertEquals("Весь мир, кроме США и РФ", elem_region.getText()); //Везде так надо сделать вместо equals
        String startDate = Init.getStash().get("st_startDate").get(0);
        String finishDate = Init.getStash().get("st_finishDate").get(0);
        Assert.assertEquals(startDate, elem_startDate.getAttribute("value"));
        Assert.assertEquals(finishDate, elem_finishDate.getAttribute("value"));
        Assert.assertEquals("false", elem_checkBoxYear.getAttribute("aria-checked"));
        Assert.assertEquals("15", elem_policyDuration.getAttribute("value"));
        Assert.assertEquals("1", elem_3_60Years.getAttribute("value"));
        Assert.assertEquals("0", elem_0_2Years.getAttribute("value"));
        Assert.assertEquals("0", elem_61_70Years.getAttribute("value"));
        click(elem_min);
        Thread.sleep(3000);
        Assert.assertEquals(elem_min.getCssValue("border-top-color"),"rgba(255, 167, 21, 1)");
        click(elem_baggage);
        Thread.sleep(3000);
        Assert.assertFalse("ERROR, Protect Baggage - ACTIVE", elem_baggage.getAttribute("class").contains("b-form-active-box"));
        Assert.assertFalse("ERROR, Sport - ACTIVE", elem_sport.getAttribute("class").contains("b-form-active-box"));
        Assert.assertFalse("ERROR, Special - ACTIVE", elem_special.getAttribute("class").contains("b-form-active-box"));
        Assert.assertFalse("ERROR, Advocate - ACTIVE", elem_advocate.getAttribute("class").contains("b-form-active-box"));
        Assert.assertFalse("ERROR, Cautious - ACTIVE", elem_cautious.getAttribute("class").contains("b-form-active-box"));
        Assert.assertEquals(elem_baggage.getCssValue("border-top-color"),"rgba(235, 237, 236, 1)");
        Assert.assertEquals(elem_sport.getCssValue("border-top-color"),"rgba(235, 237, 236, 1)");
        Assert.assertEquals(elem_special.getCssValue("border-top-color"),"rgba(235, 237, 236, 1)");
        Assert.assertEquals(elem_advocate.getCssValue("border-top-color"),"rgba(235, 237, 236, 1)");
        Assert.assertEquals(elem_cautious.getCssValue("border-top-color"),"rgba(235, 237, 236, 1)");
    }
    public void checkRegAndConfirm(){
        Assert.assertTrue("ERROR, Element Registration - ACTIVE!", elem_reg.getAttribute("class").contains("disabled"));
        Assert.assertTrue("ERROR, Element Confirm - ACTIVE!", elem_confirm.getAttribute("class").contains("disabled"));
    }
    public void checkTotalAmountMin(){
        Assert.assertEquals(Init.getStash().get("st_totalCost").get(0), elem_total.getText().trim());
    }
    public void selectEnough() throws FileNotFoundException, InterruptedException {
        click(elem_enought);
        Thread.sleep(3000);
        Assert.assertTrue("ERROR, Enough - DISACTIVE", elem_enought.getAttribute("class").contains("b-form-active-box"));
    }
    public void checkTotalAmountEnough() throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertEquals(Init.getStash().get("st_totalCostAfterClickEnough").get(0), elem_total.getText().trim());
    }
    public void pickSportAndCheckTotalAmount() throws FileNotFoundException, InterruptedException {
        click(elem_sport);
        Thread.sleep(5000);
        Assert.assertEquals(Init.getStash().get("st_totalCostAfterClickSport").get(0), elem_total.getText().trim());
    }
    public void checkTextSport(){
        Assert.assertTrue("ERROR, NO TEXT SPORT", elem_sport.getText().contains("Спортивный") &&
                elem_sport.getText().contains("Активные виды спорта") &&
                elem_sport.getText().contains("Защита спортинвентаря") &&
                elem_sport.getText().contains("Ski-pass / Лавина") &&
                elem_sport.getText().contains((String) Init.getStash().get("st_costSport").get(0)));
    }
    public void pickAlsoPrudentAndCheckTotalAmount() throws FileNotFoundException, InterruptedException {
        click(elem_cautious);
        Thread.sleep(5000);
        Assert.assertEquals(Init.getStash().get("st_totalCostWithoutBaggage").get(0), elem_total.getText().trim());
    }
    public void pickAlsoBaggageProtNoSport() throws FileNotFoundException, InterruptedException {
        click(elem_baggage);
        Thread.sleep(5000);
        click(elem_sport);
        Thread.sleep(5000);
        Assert.assertEquals(Init.getStash().get("st_totalCostWithBaggageCautious").get(0), elem_total.getText().trim());
    }
}
