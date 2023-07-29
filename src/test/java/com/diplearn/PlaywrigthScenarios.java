package com.diplearn;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.*;
import java.nio.file.Paths;


public class PlaywrigthScenarios {

    private static long startTime;
    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;


    @BeforeClass
    public static void time(){
        startTime = System.currentTimeMillis();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

    }

    @AfterClass
    public static void timeEnd(){
        playwright.close();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Tiempo total de ejecución de pruebas: " + executionTime + " milisegundos");
    }


    @BeforeMethod
    public void setUp() {
        context = browser.newContext();
        page = context.newPage();
        //page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        int screenWidth = 1920;
        int screenHeight = 1080;
        page.setViewportSize(screenWidth, screenHeight);
        page.navigate("https://www.automationtesting.co.uk/accordion.html");
    }

    @AfterMethod
    public void tearDown() {
        context.close();
    }

    @Test
    public void Calculadora (){

        page.locator("//a[text()='Calculator (JS)']").click();
        page.locator("//input[@value='1']").click();
        page.locator("//input[@value='2']").click();
        page.locator("//input[@value='3']").click();
        page.locator("//input[@value='4']").click();
        page.locator("//input[@value='5']").click();
        page.locator("//input[@value='+']").click();
        page.locator("//input[@value='6']").click();
        page.locator("//input[@value='7']").click();
        page.locator("//input[@value='8']").click();
        page.locator("//input[@value='=']").click();
    }

    @Test
    public  void CargarArchivo(){
        page.locator("//a[text()='File Upload']").click();
        page.setInputFiles("input[type='file']", Paths.get("src/main/resources/test.txt"));
    }

    @Test
    public void loader(){
        page.locator("//a[text()='Loader']").click();
        page.locator("button#loaderBtn").click();
    }
}
