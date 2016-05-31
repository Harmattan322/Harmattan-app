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
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by DadaL1fe on 20.05.2016.
 */
public class PrivateCustomersPage extends AnyPage {
    @FindBy(className = "currency-converter-date")
    private WebElement elem_date;

    @FindBy(xpath = "//label[@for='from']")
    private WebElement elem_label_from;

    @FindBy(xpath = "//label[@for='to']")
    private WebElement elem_label_to;

    @FindBy(xpath = "//div[@class='bp-widget-body']/div/div/form/div[1]/div/div")
    private WebElement elem_conv_sp1;

    @FindBy(xpath = "//div[@class='bp-widget-body']/div/div/form/div[2]/div/div")
    private WebElement elem_conv_sp2;

    @FindBy(id = "from")
    private WebElement elem_conv_from;

    @FindBy(id = "to")
    private WebElement elem_conv_to;

    @FindBy(xpath = "//div[@class='currency-converter-result']/span[1]")
    private WebElement span1;

    @FindBy(xpath = "//div[@class='currency-converter-result']/span[3]")
    private WebElement span_value1;

    @FindBy(xpath = "//div[@class='currency-converter-result']/span[4]")
    private WebElement span_equals;

    @FindBy(xpath = "//div[@class='currency-converter-result']/span[5]")
    private WebElement span_kurs;

    @FindBy(xpath = "//div[@class='currency-converter-result']/span[7]")
    private WebElement span_value2;

    @FindBy(xpath = "//div[@class='SBR-personal-rates']/table/tbody/tr[1]/td[3]")
    private WebElement kurs_EUR_sell;

    @FindBy(xpath = "//ul[@class='select2-results ps-container']/li[1]")
    private WebElement pick1_1;

    @FindBy(xpath = "//div[@id='select2-drop']/ul/li[1]")
    private WebElement pick2_1;

    @FindBy(xpath = "//div[@class='SBR-personal-rates']/table/tbody/tr[2]/td[2]")
    private WebElement kurs_USD_buy;

    @FindBy(xpath = "//div[@class='SBR-personal-rates']/table/tbody/tr[2]/td[3]")
    private WebElement kurs_USD_sell;

    public PrivateCustomersPage() throws IOException {
        new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//span[contains(text(),'Конвертер валют')]")));
    }

    private BigDecimal roundUp(double value, int digits){
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP); //метод округления
    }

    public void checkDate() throws FileNotFoundException {
        Calendar cal = new GregorianCalendar();
        DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {
            @Override
            public String[] getMonths() {
                return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                        "июля", "августа", "сентября", "октября", "ноября", "декабря"};
            }
        };
        SimpleDateFormat format1 = new SimpleDateFormat("dd MMMM yyyy", myDateFormatSymbols);
        String data = format1.format(cal.getTime());
         Assert.assertEquals(data,elem_date.getText());
    }
    public void checkConverter() throws FileNotFoundException {

        Assert.assertEquals("Поменять",elem_label_from.getText());
        Assert.assertEquals("На",elem_label_to.getText());

        Assert.assertTrue("FAIL #3.3, NO EXISTENCE listbox#1", elem_conv_sp1.isDisplayed());
        Assert.assertTrue("FAIL #3.4, NO EXISTENCE listbox#2", elem_conv_sp2.isDisplayed());

        Assert.assertTrue("FAIL #3.5, NO EXISTENCE textbox#1", elem_conv_from.isDisplayed());
        Assert.assertTrue("FAIL #3.6, NO EXISTENCE textbox#2", elem_conv_to.isDisplayed());

        Assert.assertEquals("1",span1.getText());
        Assert.assertEquals("RUB",span_value1.getText());
        Assert.assertEquals("=",span_equals.getText());

        double kursRubEur = 1 / Double.parseDouble(kurs_EUR_sell.getText());
        Assert.assertEquals(String.valueOf(roundUp(kursRubEur, 4)),span_kurs.getText());
        Assert.assertEquals("EUR",span_value2.getText());
    }
    public void setConverterRubEur() throws FileNotFoundException {
        elem_conv_from.clear();
        elem_conv_from.sendKeys("34");
        Assert.assertEquals("34",elem_conv_from.getAttribute("value"));

    }
    public void checkValueToRubEur() throws FileNotFoundException {
        double kursRubEur = 1 / Double.parseDouble(kurs_EUR_sell.getText());
        double toRubEur = kursRubEur * Double.parseDouble(elem_conv_from.getAttribute("value"));
        Assert.assertEquals(String.valueOf(roundUp(toRubEur, 2)), elem_conv_to.getAttribute("value"));
    }
    public void setConverterUsdEur() throws FileNotFoundException {
        elem_conv_sp1.click();
        pick1_1.click();
        elem_conv_from.clear();
        elem_conv_from.sendKeys("10023");
        Assert.assertEquals("10023",elem_conv_from.getAttribute("value"));
    }
    public void checkValueToUsdEur() throws FileNotFoundException {
        double kursUsdEur = Double.parseDouble(kurs_USD_buy.getText()) / Double.parseDouble(kurs_EUR_sell.getText());
        double toUsdEur = kursUsdEur * Double.parseDouble(elem_conv_from.getAttribute("value"));
        Assert.assertEquals(String.valueOf(roundUp(toUsdEur, 2)),elem_conv_to.getAttribute("value").replaceAll(" ", ""));
    }
    public void setConverterUsdUsd() throws InterruptedException, FileNotFoundException {
        elem_conv_from.clear();
        Thread.sleep(1000);
        elem_conv_from.sendKeys("5");
        Thread.sleep(1000);
        elem_conv_sp2.click();
        Thread.sleep(1000);
        pick2_1.click();
        Thread.sleep(1000);
        Assert.assertEquals("RUB",elem_conv_sp1.getText());
        Thread.sleep(1000);
        Assert.assertEquals("5",elem_conv_from.getAttribute("value"));
    }
    public void checkValueToUsdUsd() throws FileNotFoundException {
        double kursRubUsd = 1/ Double.parseDouble(kurs_USD_sell.getText());
        double toRubUsd = kursRubUsd * Double.parseDouble(elem_conv_from.getAttribute("value"));
        Assert.assertEquals(String.valueOf(roundUp(toRubUsd, 2)),elem_conv_to.getAttribute("value"));
    }
}

