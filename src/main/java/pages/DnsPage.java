package pages;

import lib.Init;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DadaL1fe on 27.05.2016.
 */
public class DnsPage extends AnyPage {
    @FindBy(xpath = "//span[contains(text(),'Производитель')]/..")
    private WebElement listbox_manufacture;


    @FindBy(xpath = "//span[contains(text(),'Цвет')]/..")
    private WebElement listbox_color;


    @FindBy(xpath = "//span[contains(text(),'Совместимость')]/..")
    private WebElement listbox_compatibility;

    @FindBy(xpath = "//span[contains(text(),'Ресурс')]/..")
    private WebElement listbox_resource;

    @FindBy(xpath = "//input[@id='f5ez_from']")
    private WebElement OnListbox_resourceS;

    @FindBy(xpath = "//input[@id='f5ez_to']")
    private WebElement OnListbox_resourceF;

    @FindBy(xpath = "//span[contains(text(),'Количество в упаковке')]/..")
    private WebElement listbox_countInPackage;

    @FindBy(xpath = "//span[contains(text(),'Поддерживаемые модели принтеров')]/..")
    private WebElement listbox_validPrinters;

    @FindBy(xpath = "//button[text()='Показать']")
    private WebElement button_Show;

    @FindBys(@FindBy(xpath = "//div[@class='item-desc']/a"))
    private List<WebElement> list_items_desc;

    @FindBys(@FindBy(xpath = "//div[@class='item-name']/a"))
    private List<WebElement> list_items_name;

    @FindBys(@FindBy(xpath = "//div[@class='thumbnail']"))
    private List<WebElement> list_goods;

    @FindBys(@FindBy(xpath = "//div[@class='thumbnail']//span[@data-of='price-total']"))
    private List<WebElement> list_prices;

    @FindBys(@FindBy(xpath = "//a[@data-commerce-target='PRODUCT_AVAILS']"))
    private List<WebElement> list_existence;

    private WebElement price;

    private BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP); //метод округления
    }


    public DnsPage() throws IOException {
        new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//h1[text()='Картриджи лазерные']")));
    }

    public void pickManufacture() throws Exception {
        try {
            if (Init.getStash().get("st_manufacture") != null) {
                for (int i = 0; i < Init.getStash().get("st_manufacture").size(); i++) {
                    ((JavascriptExecutor) Init.getDriver()).executeScript("element = document.evaluate(\"" + "//nav[@id='header-search']" + "\", document, null, XPathResult.ANY_TYPE, null).iterateNext();if (element !== null) {element.parentNode.removeChild(element);};");
                    WebElement elem = Init.getDriver().findElement(By.xpath("//span[contains(text(),'" + Init.getStash().get("st_manufacture").get(i) + "')]"));
                    ((JavascriptExecutor) Init.getDriver()).executeScript("arguments[0].scrollIntoView(true);", elem);
                    Actions clicker = new Actions(Init.getDriver());
                    clicker.moveToElement(elem, -17, 1).click().build().perform();
                    Allure.LIFECYCLE.fire(new AddParameterEvent("Производитель", Init.getStash().get("st_manufacture").get(i)));
                }
            }
        } catch (NullPointerException e) {
            Allure.LIFECYCLE.fire(new AddParameterEvent("Производитель", "не задан"));
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void pickColor() throws Exception {
        try {
            if (Init.getStash().get("st_color") != null) {
                click(listbox_color);
                for (int i = 0; i < Init.getStash().get("st_color").size(); i++) {
                    ((JavascriptExecutor) Init.getDriver()).executeScript("element = document.evaluate(\"" + "//nav[@id='header-search']" + "\", document, null, XPathResult.ANY_TYPE, null).iterateNext();if (element !== null) {element.parentNode.removeChild(element);};");
                    WebElement elem = Init.getDriver().findElement(By.xpath("//span[contains(text(),'" + Init.getStash().get("st_color").get(i) + "')]"));
                    click(elem);
                    Allure.LIFECYCLE.fire(new AddParameterEvent("Цвет", Init.getStash().get("st_color").get(i)));
                }
            }
        } catch (NullPointerException e) {
            Allure.LIFECYCLE.fire(new AddParameterEvent("Цвет", "не задан"));
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void pickCompatibility() throws Exception {
        try {
            if (Init.getStash().get("st_compatibility") != null) {
                click(listbox_compatibility);
                for (int i = 0; i < Init.getStash().get("st_compatibility").size(); i++) {
                    WebElement elem = Init.getDriver().findElement(By.xpath("//span[contains(text(),'" + Init.getStash().get("st_compatibility").get(i) + "')]"));
                    click(elem);
                    Allure.LIFECYCLE.fire(new AddParameterEvent("Совместимость", Init.getStash().get("st_compatibility").get(i)));
                }
            }
        } catch (NullPointerException e) {
            Allure.LIFECYCLE.fire(new AddParameterEvent("Совместимость", "не задана"));
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void pickResources() throws Exception {
        try {
            if (Init.getStash().get("st_resource") != null) {
                click(listbox_resource);
                click(OnListbox_resourceS);
                setText(OnListbox_resourceS, Init.getStash().get("st_resource").get(0));
                Allure.LIFECYCLE.fire(new AddParameterEvent("Значение ресурса ОТ", Init.getStash().get("st_resource").get(0)));
                if (Init.getStash().get("st_resource").size() > 1) {
                    click(OnListbox_resourceF);
                    setText(OnListbox_resourceF, Init.getStash().get("st_resource").get(1));
                    Allure.LIFECYCLE.fire(new AddParameterEvent("Значение ресурса ДО", Init.getStash().get("st_resource").get(1)));
                }
            }
        } catch (NullPointerException e) {
            Allure.LIFECYCLE.fire(new AddParameterEvent("Значение ресурса", "не задано"));
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void pickCountInPackage() throws Exception {
        try {
            if (Init.getStash().get("st_countInPackage") != null) {
                click(listbox_countInPackage);
                for (int i = 0; i < Init.getStash().get("st_countInPackage").size(); i++) {
                    WebElement elem = Init.getDriver().findElement(By.xpath("//span[contains(text(),'" + Init.getStash().get("st_countInPackage").get(i) + "')]"));
                    click(elem);
                    Allure.LIFECYCLE.fire(new AddParameterEvent("Количество в упаковке", Init.getStash().get("st_countInPackage").get(i)));
                }
            }
        } catch (NullPointerException e) {
            Allure.LIFECYCLE.fire(new AddParameterEvent("Количество в упаковке", "не задано"));
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void pickValidPrinters() throws Exception {
        try {
            if (Init.getStash().get("st_validPrinters") != null) {
                click(listbox_validPrinters);
                for (int i = 0; i < Init.getStash().get("st_validPrinters").size(); i++) {
                    ((JavascriptExecutor) Init.getDriver()).executeScript("element = document.evaluate(\"" + "//nav[@id='header-search']" + "\", document, null, XPathResult.ANY_TYPE, null).iterateNext();if (element !== null) {element.parentNode.removeChild(element);};");
                    WebElement elem = Init.getDriver().findElement(By.xpath("//span[contains(text(),'" + Init.getStash().get("st_validPrinters").get(i) + "')]"));
                    ((JavascriptExecutor) Init.getDriver()).executeScript("arguments[0].scrollIntoView();", elem);
                    click(elem);
                    Allure.LIFECYCLE.fire(new AddParameterEvent("Поддерживаемые принтеры", Init.getStash().get("st_validPrinters").get(i)));
                }
            }
        } catch (NullPointerException e) {
            Allure.LIFECYCLE.fire(new AddParameterEvent("Поддерживаемые принтеры", "не задано"));
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void pushButtonShow() throws FileNotFoundException {
        click(button_Show);
        Allure.LIFECYCLE.fire(new AddParameterEvent("Успешное нажатие", "кнопка Показать"));
        waitPageToLoad();
    }

    public void checkName() throws FileNotFoundException, InterruptedException {
        try {
            int count_name_check = 0;
            if (Init.getStash().get("st_manufacture") != null) {
                for (WebElement element : list_items_name) {
                    for (int i = 0; i < Init.getStash().get("st_manufacture").size(); i++) {
                        if (element.getText().contains(Init.getStash().get("st_manufacture").get(i))) {
                            Allure.LIFECYCLE.fire(new AddParameterEvent("Успешная проверка  имени товара №" + ++count_name_check, Init.getStash().get("st_manufacture").get(i)));
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            Allure.LIFECYCLE.fire(new AddParameterEvent("Проверяем имя каждого товара", "товары не заданы"));
            Assert.fail();
        }
    }

    public void checkDesc() throws FileNotFoundException, InterruptedException {
        int count_color_check = 0;
        int count_compatibility_check = 0;
        int count_resource_check = 0;
        int count_countInPackage = 0;
        for (WebElement element : list_items_desc) {
            try {
                if (Init.getStash().get("st_color") != null) {
                    for (int i = 0; i < Init.getStash().get("st_color").size(); i++) {
                        if (element.getText().contains(Init.getStash().get("st_color").get(i))) {
                            Allure.LIFECYCLE.fire(new AddParameterEvent("Успешная проверка  цвета товара №" + ++count_color_check, Init.getStash().get("st_color").get(i)));
                        }
                    }
                }
            } catch (NullPointerException e) {
                Allure.LIFECYCLE.fire(new AddParameterEvent("Проверяем цвет товаров", "не задан"));
                Assert.fail();
            }
            try {
                if (Init.getStash().get("st_compatibility") != null) {
                    for (int i = 0; i < Init.getStash().get("st_compatibility").size(); i++) {
                        if (element.getText().contains(Init.getStash().get("st_compatibility").get(i))) {
                            Allure.LIFECYCLE.fire(new AddParameterEvent("Успешная проверка  совместимости товара №" + ++count_compatibility_check, Init.getStash().get("st_compatibility").get(i)));
                        }
                    }
                }
            } catch (NullPointerException e) {
                Allure.LIFECYCLE.fire(new AddParameterEvent("Проверяем совместимость товара", "не задана"));
                Assert.fail();
            }
            try {
                if (Init.getStash().get("st_resource") != null) {
                    Pattern pattern = Pattern.compile("[0-9]+[0]+[0]+");
                    Matcher matcher = pattern.matcher(element.getText());
                    if (Init.getStash().get("st_resource").get(0).isEmpty() || Init.getStash().get("st_resource").get(1).isEmpty()) {
                        click(listbox_resource);
                        if (Init.getStash().get("st_resource").get(0).isEmpty() && !Init.getStash().get("st_resource").get(1).isEmpty()) {
                            while (matcher.find()) {
                                Assert.assertTrue("Resource is not valid", Integer.parseInt(matcher.group()) <= Integer.parseInt(Init.getStash().get("st_resource").get(1)));
                                Allure.LIFECYCLE.fire(new AddParameterEvent("Успешная проверка значения ресурса у товара товара ДО №" + ++count_resource_check, Init.getStash().get("st_resource").get(1)));
                            }
                        } else if (!Init.getStash().get("st_resource").get(0).isEmpty() && Init.getStash().get("st_resource").get(1).isEmpty()) {
                            while (matcher.find()) {
                                Assert.assertTrue("Resource is not valid", Integer.parseInt(matcher.group()) >= Integer.parseInt(Init.getStash().get("st_resource").get(0)));
                                Allure.LIFECYCLE.fire(new AddParameterEvent("Успешная проверка значения ресурса у товара товара ОТ №" + ++count_resource_check, Init.getStash().get("st_resource").get(0)));
                            }
                        } else if (Init.getStash().get("st_resource").get(0).isEmpty() && Init.getStash().get("st_resource").get(1).isEmpty()) {
                            String number_resourceS = OnListbox_resourceS.getAttribute("placeholder");
                            String number_resourceF = OnListbox_resourceF.getAttribute("placeholder");
                            Assert.assertTrue("Resource is not valid", Integer.parseInt(number_resourceS) <= Integer.parseInt(number_resourceF));
                            Allure.LIFECYCLE.fire(new AddParameterEvent("Успешная проверка значений ресурса у товара товара ОТ и ДО №" + ++count_resource_check, number_resourceS + number_resourceF));
                        }
                    } else {
                        while (matcher.find()) {
                            Assert.assertTrue("Resource is not valid", Integer.parseInt(Init.getStash().get("st_resource").get(0))
                                    <= Integer.parseInt(matcher.group()) && Integer.parseInt(matcher.group())
                                    <= Integer.parseInt(Init.getStash().get("st_resource").get(1)));
                            Allure.LIFECYCLE.fire(new AddParameterEvent("Успешная проверка значений ресурса у товара ОТ и ДО №" + ++count_resource_check, Init.getStash().get("st_resource").get(0) + " - " + Init.getStash().get("st_resource").get(1)));
                        }
                    }
                }
            } catch (NullPointerException e) {
                Allure.LIFECYCLE.fire(new AddParameterEvent("Проверяем значение ресурса", "не задано"));
                Assert.fail();
            } catch (ArrayIndexOutOfBoundsException e1) {
                System.out.println("OVER 9000 ARRAY RESOURCE!!");
                Assert.fail();
            }
            try {
                if (Init.getStash().get("st_countInPackage") != null) {
                    for (int i = 0; i < Init.getStash().get("st_countInPackage").size(); i++) {
                        if (element.getText().contains(Init.getStash().get("st_countInPackage").get(i))) {
                            Allure.LIFECYCLE.fire(new AddParameterEvent("Успешная проверка на кол-во в упаковке товара № " + ++count_countInPackage, Init.getStash().get("st_countInPackage").get(i)));
                        }
                    }
                }
            } catch (NullPointerException e) {
                Allure.LIFECYCLE.fire(new AddParameterEvent("Проверяем кол-во в упаковке", "не задано"));
                Assert.fail();
            }

        }
    }

    public void findAverage() throws FileNotFoundException {
        try {
            int countSize = 0;
            double sum = 0;

            for (int i = 0; i < list_existence.size(); i++) {
                if (list_existence.get(i).getText().equals("Есть в наличии")) {
                    countSize++;
                }
            }
            int average = Integer.parseInt(String.valueOf(roundUp(((double) countSize / 2.0), 0)));
            System.out.println("average SOLVING: " + average);
            WebElement average_price = list_existence.get(average).findElement(By.xpath("./../../../../..//span[@data-of='price-total']"));
            WebElement average_good = average_price.findElement(By.xpath("./../../../../..//div[@class='item-name']/a"));
            System.out.println("Товар: " + average_good.getText() + " в наличии со средней ценой: " + average_price.getText());
            Allure.LIFECYCLE.fire(new AddParameterEvent("Товар: " + average_good.getText(), " в наличии со средней ценой: " + average_price.getText()));
        } catch (NullPointerException e) {
            Allure.LIFECYCLE.fire(new AddParameterEvent("Товар в наличии со средней ценой", "товаров не найдено"));
            Assert.fail();
        }
    }


    public void findCheap() throws FileNotFoundException {
        try {
            Pattern pattern = Pattern.compile("[0-9]+");
            WebElement min_price_elem = null;
            int priceCheap = 0;
            for (int i = 0; i < list_existence.size(); i++) {

                Matcher matcher = pattern.matcher(list_existence.get(i).getText());
                System.out.println("All values Cheap: " + list_existence.get(i).getText());
                if (matcher.find()) {
                    System.out.println("Заказ (сколько дней): " + matcher.group());
                    Allure.LIFECYCLE.fire(new AddParameterEvent("Ожидание заказа", matcher.group()));
                    if (list_existence.get(i).getText().contains(matcher.group())) {

                        price = list_existence.get(i).findElement(By.xpath("./../../../../..//span[@data-of='price-total']"));
                        priceCheap = Integer.parseInt(price.getText().replaceAll(" ", ""));
                        System.out.println("price with waiting some days: " + priceCheap);
                        min_price_elem = price.findElement(By.xpath("./../../../../..//div[@class='item-name']/a"));
                        break;
                    }
                }
            }
            if (min_price_elem != null) {
                System.out.println("Товар с наим. ценой под заказ: " + min_price_elem.getText());
                Allure.LIFECYCLE.fire(new AddParameterEvent("Товар: " + min_price_elem.getText(), " с наименьшей ценой на ближайший заказ: " + priceCheap));
                click(min_price_elem);
                Thread.sleep(5000);
            }

        } catch (NullPointerException e) {
            Allure.LIFECYCLE.fire(new AddParameterEvent("Ищем товар с наименьшей ценой на ближайший заказ", "товаров не найдено"));
            Assert.fail();
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
