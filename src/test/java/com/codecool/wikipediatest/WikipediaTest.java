package com.codecool.wikipediatest;

import org.junit.jupiter.api.*;
import org.junit.platform.commons.function.Try;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.time.Duration.ofSeconds;


public class WikipediaTest {

    public static WebDriver webDriver;
    public static WebDriverWait wait;
    private final String URL = "https://en.wikipedia.org/";

    public void mobileMode(){
        webDriver.manage().window().setSize(new Dimension(375,812));
    }

    public void desktopMode(){
        webDriver.manage().window().maximize();
    }

    private void logOut() {
        WebElement logOutLink = webDriver.findElement(By.linkText("Log out"));
        logOutLink.click();
    }

    private void navigateToHome() {
        WebElement homeButton = webDriver.findElement(By.xpath("//*[@id=\"p-logo\"]/a"));
        homeButton.click();
        String homeURL = URL + "wiki/Main_Page";
        Assertions.assertEquals(homeURL, webDriver.getCurrentUrl());
    }


    private void testWikiDesktop() {
        webDriver.get(URL);
        desktopMode();
    }


    @BeforeAll
    static void setDriverProperty() {
        System.setProperty("webdriver.chrome.driver", "C://Program Files (x86)/Selenium/chromedriver.exe");
    }

    @BeforeEach
    void setWebDriver() {
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver,20);
    }

// Testing Sign In


    @Test
    public void testWikiMobile() {
        webDriver.get(URL);
        desktopMode();
        logInDesktop();
    }



    @Test
    public void logInDesktop() {
        testWikiDesktop();
        WebElement loginLink = webDriver.findElement(By.linkText("Log in"));
        loginLink.click();

        WebElement userName = webDriver.findElement(By.id("wpName1"));
        userName.clear();
        userName.sendKeys("Szpetertester");

        WebElement psw = webDriver.findElement(By.id("wpPassword1"));
        psw.clear();
        psw.sendKeys("Asdqwe123");

        WebElement loginButton = webDriver.findElement(By.id("wpLoginAttempt"));
        loginButton.click();
        Assertions.assertEquals(URL+"wiki/Main_Page",webDriver.getCurrentUrl());

        WebElement loggedUser = webDriver.findElement(By.xpath("//*[@id='pt-userpage']/a"));
        Assertions.assertEquals("Szpetertester", loggedUser.getText());
    }

    @Test
    public void logInDesktopWithFileInput() {
        testWikiDesktop();

        FileUtils utils = new FileUtils();
        String[] credential = utils.readCredential();

        WebElement loginLink = webDriver.findElement(By.linkText("Log in"));
        loginLink.click();

        WebElement userName = webDriver.findElement(By.id("wpName1"));
        userName.clear();
        userName.sendKeys(credential[0]);

        WebElement psw = webDriver.findElement(By.id("wpPassword1"));
        psw.clear();
        psw.sendKeys(credential[1]);

        WebElement loginButton = webDriver.findElement(By.id("wpLoginAttempt"));
        loginButton.click();
        Assertions.assertEquals(URL+"wiki/Main_Page",webDriver.getCurrentUrl());

        WebElement loggedUser = webDriver.findElement(By.xpath("//*[@id='pt-userpage']/a"));
        Assertions.assertEquals("Szpetertester", loggedUser.getText());
    }

    @Test
    public void searchWikiByButton(){
        String searchFor = "Selenium";
        logInDesktop();
        WebElement searchField = webDriver.findElement(By.id("searchInput"));
        searchField.clear();
        searchField.sendKeys(searchFor);

        WebElement searchButton = webDriver.findElement(By.id("searchButton"));
        searchButton.click();

        Assertions.assertEquals(URL+"wiki/Selenium", webDriver.getCurrentUrl());
        Assertions.assertEquals("Selenium", webDriver.findElement(By.id("firstHeading")).getText());

        navigateToHome();

    }

    @Test
    public void searchWikiSaveToFile(){
        String searchFor = "Superman";
        logInDesktopWithFileInput();

        WebElement searchField = webDriver.findElement(By.id("searchInput"));
        searchField.clear();
        searchField.sendKeys(searchFor);

        WebElement searchButton = webDriver.findElement(By.id("searchButton"));
        searchButton.click();

        WebElement firstHeading = webDriver.findElement((By.xpath("//*[@id=\"firstHeading\"]")) );
        WebElement paragraph = webDriver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[2]"));

        FileUtils utils = new FileUtils();
        utils.writeToFile(firstHeading.getText(), paragraph.getText());


/*
        Assertions.assertEquals(URL+"wiki/Selenium", webDriver.getCurrentUrl());
        Assertions.assertEquals("Selenium", webDriver.findElement(By.id("firstHeading")).getText());
*/

        navigateToHome();

    }

    @Test
    public void searchWikiByEnter() throws InterruptedException {
        String searchFor = "Selenium";
        logInDesktop();
        WebElement searchField = webDriver.findElement(By.id("searchInput"));
        searchField.clear();
        searchField.sendKeys(searchFor);
        Thread.sleep(100);
        searchField.sendKeys("\n");
        WebElement firstHeading = webDriver.findElement(By.id("firstHeading"));
        String valuesText = wait.until(ExpectedConditions.visibilityOf(firstHeading)).getText();
        Assertions.assertEquals("Selenium", valuesText);

        navigateToHome();

    }

    @Test
    public void searchWikiListed() throws InterruptedException {
        logInDesktop();
        WebElement searchField = webDriver.findElement(By.id("searchInput"));
        String searchFor = "Framework";
        searchField.clear();
        searchField.sendKeys(searchFor);
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/a[3]/div/span"))));

        WebElement firstResult = webDriver.findElement(By.cssSelector("body > div.suggestions > div > a:nth-child(1) > div"));
        firstResult.click();
        String expectedLink = "https://en.wikipedia.org/wiki/Framework";
        Assertions.assertEquals(expectedLink,webDriver.getCurrentUrl());

        Assertions.assertEquals(searchFor, webDriver.findElement(By.id("firstHeading")).getText());

        navigateToHome();

    }

    @Test
    public void changeFontTest() throws InterruptedException {
        logInDesktop();
        WebElement languageSetting = webDriver.findElement(By.xpath("//*[@id=\"p-lang\"]/button"));
        languageSetting.click();

        wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("uls-display-settings-fonts-tab"))));

        WebElement fontsSetting = webDriver.findElement(By.id("uls-display-settings-fonts-tab"));
        fontsSetting.click();
        WebElement downloadFontsCheckbox = webDriver.findElement(By.id("webfonts-enable-checkbox"));
        //downloadFontsCheckbox.clear();
        downloadFontsCheckbox.click();
        WebElement dropDownComic = webDriver.findElement(By.cssSelector("#content-font-selector > option:nth-child(1)"));
        dropDownComic.click();
        WebElement applyButton = webDriver.findElement(By.xpath("//*[@id=\"language-settings-dialog\"]/div[3]/div/button[2]"));
        applyButton.click();
        // WebElement closeLanguageWindow = webDriver.findElement(By.xpath("//*[@id=\"language-settings-dialog\"]/div[3]/div/button[1]"));
        //closeLanguageWindow.click();
        // wait.until((ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"language-settings-dialog\"]/div[3]/div/button[2]"))));
        // WebElement firstResult = new WebDriverWait(webDriver, Duration.ofSeconds(10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"language-settings-dialog\"]/div[3]/div/button[2]")));
        Thread.sleep(1000);

        languageSetting.click();
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("uls-display-settings-fonts-tab"))));
        WebElement fontSelector = webDriver.findElement(By.id("content-font-selector"));

        //Assertions.assertEquals("CominNeue", fontSelector.getText());
        fontsSetting.click();
        downloadFontsCheckbox.click();
        applyButton.click();


        // wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/a[3]/div/span"))));




    }


    /*
    @Test

    public void searchWikiParagraph() throws InterruptedException {
        logInDesktop();

        WebElement searchField = webDriver.findElement(By.id("searchInput"));

        String searchFor = "Selenium (software)";
        searchField.clear();
        searchField.sendKeys(searchFor);
        WebElement searchButton = webDriver.findElement(By.id("searchButton"));
        searchButton.click();

        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toc\"]/div"))));

        // WebElement  relevantExpression = webDriver.findElement(By.xpath("//*[text()='Selenium Grid is a server that allows tests to use web browser']"));
        // String relevantExpression = "Selenium Grid is a server that allows tests to use web browser";

        WebElement  relevantExpression = webDriver.findElement(By.linkText("Grid is a server that allows tests to use web browser"));
        Assertions.assertTrue(relevantExpression.isDisplayed());



        // WebElement bodyTexts = (WebElement) webDriver.findElements(By.tagName("body"));




    }
*/


    @Test
    public void seleniumGrid(){
        logInDesktop();
        boolean relevantExpressionPresents = false;
        webDriver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("Selenium");
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/a[3]/div/span")));
        webDriver.findElement(By.xpath("/html/body/div[6]/div/a[3]/div/span")).click();
        List<WebElement> allElements = webDriver.findElements(By.xpath("//*"));
        for (WebElement paragraph : allElements) {
            {
                try{
                    if (paragraph.getText().contains("Selenium Grid")) {
                        relevantExpressionPresents = true;
                        break;
                    }
                }catch (Exception ignored){
                }
            }
        }
        Assertions.assertTrue(relevantExpressionPresents);
    }


        @AfterEach
    void quitWebDriver(){
        logOut();
        webDriver.quit();
    }


}
