package stepDefinitions;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import lib.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AnyPage;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.exceptions.PageInitializationException;
import ru.sbtqa.tag.pagefactory.support.Environment;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.AddParameterEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by DadaL1fe on 18.05.2016.
 */
public class CommonStepDefinitions {
    @Before
    public void loadProperty() {
        FileInputStream beforeInput = null;
        try {
            beforeInput = new FileInputStream("src/main/resources/config/application.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(beforeInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            String manufacture = prop.getProperty("Manufacture");
//            List<String> listManufacture = Arrays.asList(manufacture.split(";"));
//            Init.setStash("st_manufacture", listManufacture);
//        } catch (NullPointerException e1) {
//            System.out.println("Проперти, отсутствует значение Manufacture");
//            Allure.LIFECYCLE.fire(new AddParameterEvent("Проперти", "отсутствует значение Manufacture"));
//        }
//
//        try {
//            String color = prop.getProperty("Color");
//            List<String> listColor = Arrays.asList(color.split(";"));
//            Init.setStash("st_color", listColor);
//        } catch (NullPointerException e1) {
//            System.out.println("Проперти, отсутствует значение Color");
//            Allure.LIFECYCLE.fire(new AddParameterEvent("Проперти", "отсутствует значение Color"));
//        }
//
//        try {
//            String compatibility = prop.getProperty("Compatibility");
//            List<String> listCompatibility = Arrays.asList(compatibility.split(";"));
//            Init.setStash("st_compatibility", listCompatibility);
//        } catch (NullPointerException e1) {
//            System.out.println("Проперти, отсутствует значение Compatibility");
//            Allure.LIFECYCLE.fire(new AddParameterEvent("Проперти", "отсутствует значение Compatibility"));
//        }
//
//        try {
//            String resources = prop.getProperty("Resource");
//            List<String> listResources = Arrays.asList(resources.split(";"));
//            Init.setStash("st_resource", listResources);
//        } catch (NullPointerException e1) {
//            System.out.println("Проперти, отсутствует значение Resource");
//            Allure.LIFECYCLE.fire(new AddParameterEvent("Проперти", "отсутствует значение Resource"));
//        }
//
//        try {
//            String countInPackage = prop.getProperty("CountInPackage");
//            List<String> listCountInPackage = Arrays.asList(countInPackage.split(";"));
//            Init.setStash("st_countInPackage", listCountInPackage);
//        } catch (NullPointerException e1) {
//            System.out.println("Проперти, отсутствует значение Count In Package");
//            Allure.LIFECYCLE.fire(new AddParameterEvent("Проперти", "отсутствует значение Count In Package"));
//        }
//
//        try {
//            String validPrinters = prop.getProperty("ValidPrinters");
//            List<String> listValidPrinters = Arrays.asList(validPrinters.split(";"));
//            Init.setStash("st_validPrinters", listValidPrinters);
//        } catch (NullPointerException e1) {
//            System.out.println("Проперти, отсутствует значение Valid Printers");
//            Allure.LIFECYCLE.fire(new AddParameterEvent("Проперти", "отсутствует значение Valid Printers"));
//        }

        try {
            String URL = prop.getProperty("URL");
            List<String> listURL = Arrays.asList(URL.split(";"));
            Init.setStash("st_URL", listURL);
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        }

        try {
            String browser = prop.getProperty("Browser");
            List<String> listBrowser = Arrays.asList(browser.split(";"));
            Init.setStash("st_browser", listBrowser);
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        }

//        try {
//            String startDate = prop.getProperty("StartDate");
//            List<String> listStartDate = Arrays.asList(startDate.split(";"));
//            Init.setStash("st_startDate", listStartDate);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String finishDate = prop.getProperty("FinishDate");
//            List<String> listFinishDate = Arrays.asList(finishDate.split(";"));
//            Init.setStash("st_finishDate", listFinishDate);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String totalCost = prop.getProperty("TotalCost");
//            List<String> listTotalCost = Arrays.asList(totalCost.split(";"));
//            Init.setStash("st_totalCost", listTotalCost);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String totalCostAfterClickEnough = prop.getProperty("TotalCostAfterClickEnough");
//            List<String> listTotalCostAfterClickEnough = Arrays.asList(totalCostAfterClickEnough.split(";"));
//            Init.setStash("st_totalCostAfterClickEnough", listTotalCostAfterClickEnough);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String totalCostAfterClickSport = prop.getProperty("TotalCostAfterClickSport");
//            List<String> listTotalCostAfterClickSport = Arrays.asList(totalCostAfterClickSport.split(";"));
//            Init.setStash("st_totalCostAfterClickSport", listTotalCostAfterClickSport);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String costSport = prop.getProperty("CostSport");
//            List<String> listCostSport = Arrays.asList(costSport.split(";"));
//            Init.setStash("st_costSport", listCostSport);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String totalCostWithoutBaggage = prop.getProperty("TotalCostWithoutBaggage");
//            List<String> listTotalCostWithoutBaggage = Arrays.asList(totalCostWithoutBaggage.split(";"));
//            Init.setStash("st_totalCostWithoutBaggage", listTotalCostWithoutBaggage);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String totalCostWithBaggageCautious = prop.getProperty("TotalCostWithBaggageCautious");
//            List<String> listTotalCostWithBaggageCautious = Arrays.asList(totalCostWithBaggageCautious.split(";"));
//            Init.setStash("st_totalCostWithBaggageCautious", listTotalCostWithBaggageCautious);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
    }

    @Дано("^пользователь переходит по заданной странице$")
    public void goToLink() throws FileNotFoundException {
        Allure.LIFECYCLE.fire(new AddParameterEvent("Переход на страницу ", Init.getStash().get("st_URL").get(0)));
        Init.getDriver().get(Init.getStash().get("st_URL").get(0));
    }

//    @Given("^Check Date$")
//    public void checkDate() throws IOException {
//        PrivateCustomersPage privateCustomersPage = new PrivateCustomersPage();
//        privateCustomersPage.checkDate();
//    }
//
//    @Given("^Check Converter$")
//    public void checkConverter() throws IOException {
//        PrivateCustomersPage privateCustomersPage = new PrivateCustomersPage();
//        privateCustomersPage.checkConverter();
//    }
//
//    @Given("^Set on Converter RUB and EUR$")
//    public void setConverterRubEur() throws IOException {
//        PrivateCustomersPage privateCustomersPage = new PrivateCustomersPage();
//        privateCustomersPage.setConverterRubEur();
//    }
//
//    @Given("^check value of RUB to EUR$")
//    public void checkValueToRubEur() throws IOException {
//        PrivateCustomersPage privateCustomersPage = new PrivateCustomersPage();
//        privateCustomersPage.checkValueToRubEur();
//    }
//
//    @Given("^Set on Converter USD and EUR$")
//    public void setConverterUsdEur() throws IOException {
//        PrivateCustomersPage privateCustomersPage = new PrivateCustomersPage();
//        privateCustomersPage.setConverterUsdEur();
//    }
//
//    @Given("^check value of USD to EUR$")
//    public void checkValueToUsdEur() throws IOException {
//        PrivateCustomersPage privateCustomersPage = new PrivateCustomersPage();
//        privateCustomersPage.checkValueToUsdEur();
//    }
//
//    @Given("^Set on Converter USD and USD$")
//    public void setConverterUsdUsd() throws IOException, InterruptedException {
//        PrivateCustomersPage privateCustomersPage = new PrivateCustomersPage();
//        privateCustomersPage.setConverterUsdUsd();
//    }
//
//    @Given("^check value of USD to USD$")
//    public void checkValueToUsdUsd() throws IOException {
//        PrivateCustomersPage privateCustomersPage = new PrivateCustomersPage();
//        privateCustomersPage.checkValueToUsdUsd();
//    }
//
//    @Given("^Check Polis Fields$")
//    public void checkPolisFields() throws IOException, InterruptedException {
//        TravelInsurancePage travelInsurancePage = new TravelInsurancePage();
//        travelInsurancePage.checkPolisFields();
//    }
//
//    @Given("^Check Registration and Confirm$")
//    public void checkRegAndConfirm() throws IOException, InterruptedException {
//        TravelInsurancePage travelInsurancePage = new TravelInsurancePage();
//        travelInsurancePage.checkRegAndConfirm();
//    }
//
//    @Given("^Check Total Amount Min$")
//    public void checkTotalAmountMin() throws IOException, InterruptedException {
//        TravelInsurancePage travelInsurancePage = new TravelInsurancePage();
//        travelInsurancePage.checkTotalAmountMin();
//    }
//
//    @Given("^Select Enough$")
//    public void selectEnough() throws IOException, InterruptedException {
//        TravelInsurancePage travelInsurancePage = new TravelInsurancePage();
//        travelInsurancePage.selectEnough();
//    }
//
//    @Given("^Check Total Amount Enough$")
//    public void checkTotalAmountEnough() throws IOException, InterruptedException {
//        TravelInsurancePage travelInsurancePage = new TravelInsurancePage();
//        travelInsurancePage.checkTotalAmountEnough();
//    }
//
//    @Given("^Pick Sport and Check Total Amount$")
//    public void pickSportAndCheckTotalAmount() throws IOException, InterruptedException {
//        TravelInsurancePage travelInsurancePage = new TravelInsurancePage();
//        travelInsurancePage.pickSportAndCheckTotalAmount();
//    }
//
//    @Given("^Check Text Sport$")
//    public void checkTextSport() throws IOException, InterruptedException {
//        TravelInsurancePage travelInsurancePage = new TravelInsurancePage();
//        travelInsurancePage.checkTextSport();
//    }
//
//    @Given("^Pick also Prudent and Check Total Amount$")
//    public void pickAlsoPrudentAndCheckTotalAmount() throws IOException, InterruptedException {
//        TravelInsurancePage travelInsurancePage = new TravelInsurancePage();
//        travelInsurancePage.pickAlsoPrudentAndCheckTotalAmount();
//    }
//
//    @Given("^Pick also Baggage protection, but no Sport$")
//    public void pickAlsoBaggageProtNoSport() throws IOException, InterruptedException {
//        TravelInsurancePage travelInsurancePage = new TravelInsurancePage();
//        travelInsurancePage.pickAlsoBaggageProtNoSport();
//    }
//
//    @Given("^check Green Icons$")
//    public void checkGreenIcons() throws IOException, InterruptedException {
//        AtmPage atmPage = new AtmPage();
//        atmPage.checkGreenIcons();
//    }
//
//    @Given("^check Minimal count Location$")
//    public void checkMinLocation() throws IOException {
//        AtmPage atmPage = new AtmPage();
//        atmPage.checkMinLocation();
//    }
//
//    @Given("^check Order Locations$")
//    public void checkOrderLocations() throws IOException {
//        AtmPage atmPage = new AtmPage();
//        atmPage.checkOrderLocations();
//    }
//
//    @Given("^pick checkbox Payment Device$")
//    public void choosePaymentDevice() throws IOException, InterruptedException {
//        AtmPage atmPage = new AtmPage();
//        atmPage.choosePaymentDevice();
//    }
//
//    @Given("^push button Show More$")
//    public void pushShowMore() throws IOException, InterruptedException {
//        AtmPage atmPage = new AtmPage();
//        atmPage.pushShowMore();
//    }
//
//    @Given("^remove checkbox Offices$")
//    public void uncheckOffice() throws IOException, InterruptedException {
//        AtmPage atmPage = new AtmPage();
//        atmPage.uncheckOffice();
//    }
//
//    @Given("^pick Criteria$")
//    public void pickCriteria() throws Exception {
//        DnsPage dnsPage = new DnsPage();
//        dnsPage.pickManufacture();
//        dnsPage.pickColor();
//        dnsPage.pickCompatibility();
//        dnsPage.pickResources();
//        dnsPage.pickCountInPackage();
//        dnsPage.pickValidPrinters();
//    }
//
//    @Given("^push Button$")
//    public void pushButton() throws IOException {
//        DnsPage dnsPage = new DnsPage();
//        dnsPage.pushButtonShow();
//    }
//
//    @Given("^check Name$")
//    public void checkName() throws Exception {
//        DnsPage dnsPage = new DnsPage();
//        dnsPage.checkName();
//    }
//
//    @Given("^check Desc$")
//    public void checkDesc() throws Exception {
//        DnsPage dnsPage = new DnsPage();
//        dnsPage.checkDesc();
//    }
//
//    @Given("^find Average Good$")
//    public void findAverage() throws IOException {
//        DnsPage dnsPage = new DnsPage();
//        dnsPage.findAverage();
//    }
//
//    @Given("^find Cheap Goods with Waiting$")
//    public void findCheap() throws IOException {
//        DnsPage dnsPage = new DnsPage();
//        dnsPage.findCheap();
//    }

//    @И("^(?:пользователь |он |)(?:находится на странице|открывается страница|открывается вкладка мастера) \"(.*?)\"$")
//    public void openPage(String title) throws PageInitializationException {
//        if (!PageFactory.getWebDriver().getWindowHandles().isEmpty()) {
//            for (String windowHandle : PageFactory.getWebDriver().getWindowHandles()) {
//                PageFactory.getWebDriver().switchTo().window(windowHandle);
//            }
//        }
//        PageFactory.getInstance().getPage(title);
//    }
//
//    @И("^(?:пользователь |он |)\\((.*?)\\) (?:с параметром |)\"([^\"]*)\"$")
//    public void userActionOneParam(String action, String param) throws PageInitializationException, NoSuchMethodException {
//        PageFactory.getInstance().getCurrentPage().executeMethodByTitle(action, param);
//    }
//
//    @И("^(?:пользователь |он |)\\((.*?)\\)$")
//    public void userActionZeroParam(String action) throws PageInitializationException, NoSuchMethodException {
//        PageFactory.getInstance().getCurrentPage().executeMethodByTitle(action);
//    }

    @After
    public void quit() throws FileNotFoundException {
        Init.clearStash();
        Init.getDriver().quit();
    }
}
