package com.DSN.framework;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.apache.xpath.operations.String;
import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Browser {

//====================================================================================================
    private static ThreadLocal<String>          logInURL    = new ThreadLocal<>();
    private static ThreadLocal<Global.eLoginDT> logInUSER   = new ThreadLocal<>();
    public static void              setUSER (Global.eLoginDT sUSER){
        Log.info("USER set to: "+sUSER.name());
        logInUSER.set(sUSER);
    }
    public static Global.eLoginDT   getUSER (){
        Global.eLoginDT ee = logInUSER.get();
        if (logInUSER.get()==null){
            logInUSER.set(Global.DEF_USER);
        }
        return logInUSER.get();
    }
    public static void setURL (String sURL){
        logInURL.set(sURL);
    }
    public static String getURL (){
        if (logInURL.get()!=null){ logInURL.set(Global.DEF_URL); }
        return logInURL.get();
    }
    //=================================================================================================
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<NgWebDriver> ngWebDriver = new ThreadLocal<>();

    public static RemoteWebDriver get() {
        if (driver.get()==null) {
            init();
        }
        return driver.get();
    }

    public static NgWebDriver getNG() {
        return ngWebDriver.get();
    }

    private static void init() {
//        ChromeDriverManager.getInstance().forceCache().setup();// ChromeDriverManager.getInstance().version("2.24").arch64().setup();//TODO
        ChromeDriverManager.getInstance().setup("2.25");
        //=================================================================
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");//get().manage().window().maximize();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver.set(new FirefoxDriver(capabilities));//driver.set(new ChromeDriver());
        //=================================================================
        get().manage().timeouts().pageLoadTimeout(Global.DEF_PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        get().manage().timeouts().setScriptTimeout(Global.DEF_SCRIPT_TIMEOUT, TimeUnit.SECONDS);
        get().manage().timeouts().implicitlyWait(Global.DEF_IMPLICITY_WAIT_SEC, TimeUnit.SECONDS);
        //=================================================================
        ngWebDriver.set(new NgWebDriver(driver.get()));
    }

    public static void close() {
        get().quit();
        driver.remove();
        ngWebDriver.remove();
    }

    public static void restart() {
        close();
        get();
    }

    public static boolean isAlertPresent() {
        boolean presentFlag = false;
        try {
            Alert alert = Browser.get().switchTo().alert();
            presentFlag = true;
            //alert.accept();
        } catch (Exception ex) {
            //NoAlertPresentException
        }
        return presentFlag;
    }

    public static void waitForAngular() {
        /*  NOTE: Instruct webdriver to wait until Angular has finished rendering and has no outstanding $http or $timeout calls before continuing.
            Note that Protractor automatically applies this command before every WebDriver action
        */
        //---
//        waitForAngular_script(); //in case if ngWebDriver wouldn't work properly
        waitForAngular_ngWebDriver();
    }

    public static void waitForAngular_ngWebDriver() {
        long timeStart = System.currentTimeMillis();
        getNG().waitForAngularRequestsToFinish();
        long timeElapsed = (System.currentTimeMillis() - timeStart)/1000;
        if (timeElapsed > 4 ){
            Log.info("Real waiting by waitForAngular_ngWebDriver() was more than 4 seconds: " + timeElapsed+" ");
        }
    }
//====================================================================================================
    public static WebElement waitElement(By by, int... iSecToWait){
        long timeStart = System.currentTimeMillis();
        int iSecToWaitLoc = iSecToWait.length>0?iSecToWait[0]:0;
        waitForAngular();
        WebElement we = null;
        try {
            we = (new WebDriverWait(get(), iSecToWaitLoc)).
                    until(ExpectedConditions.visibilityOfElementLocated(by));//if this invoked from waitAndClick we will wait 2 sec by def
        }catch (Exception e){}
        //===
        long timeElapsed = (System.currentTimeMillis() - timeStart)/1000;
        if (timeElapsed > 2 ){
            Log.info("Real waiting of: "+by+" element was more than 2 seconds: " + timeElapsed+" ");
        }
        return we;
    }
//====================================================================================================
    public static boolean waitAndClick(By by, int... iSecToWait){
        int iSecToWaitLoc = iSecToWait.length>0?iSecToWait[0]:Global.DEF_TIME_TO_WAIT_LONG_SEC;
        waitForAngular();
        WebElement we = waitElement(by, iSecToWaitLoc);
        if (we != null){
            we.click();
            return true;
        }else {
            Log.info("WebElement by: " + by + " wasn't found");
            return false;
        }
    }

    public static boolean handleMsg(String sMsgToWait, boolean... bThrowError){
        waitForAngular();
        boolean bThrowErrorLocal = (bThrowError.length > 0)?bThrowErrorLocal = bThrowError[0]:true;
        try{
            String sMsgTxtActual	= "";
            String sXPath_FlashMsg	= "???";
            try{
                sMsgTxtActual = (new WebDriverWait(get(), 3)).
                        until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXPath_FlashMsg))).getText();}
            catch (Exception e){}
            if (sMsgTxtActual.equals(sMsgToWait)){
                Log.info("[SUCCESS] Flash message: '" + sMsgTxtActual + "'");
                return true;
            }else{
                Log.info("[BUG] Flash message: '" + sMsgTxtActual + "'");
                Assert.assertTrue(!bThrowErrorLocal, "[BUG] Unexpected FlashMSG: '"+sMsgTxtActual+"' EXPECTED: '"+sMsgToWait+"'");
            }
        }catch (Exception e){
            Log.info("[!!!] Flash msg wasn't handled correctly!");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean handlePromptMsg(String sMsgPrompt, String sButtonToClick, int... iSecToWait){
        waitForAngular();
        int iSecToWaitLoc = iSecToWait.length>0?iSecToWait[0]:Global.DEF_TIME_TO_WAIT_LONG_SEC;//def
        String xPath_prompt = "???";
        String xPath_YesButton = "??";
        if (!sButtonToClick.equals("")){
            xPath_YesButton = "???";
        }
        if (waitElement(By.xpath(xPath_prompt), iSecToWaitLoc) != null){
            waitAndClick(By.xpath(xPath_YesButton),Global.DEF_TIME_TO_WAIT_SHORT_SEC);
            return true;
        } else if (!sMsgPrompt.equals("")){
            Log.info("Expected msg: "+sMsgPrompt+" didn't appear!");
        }
        return false;
    }

    public static void sleep(int... iSecToWait){
        int iSecToWaitLoc = iSecToWait.length>0?iSecToWait[0]:1;
        try {
            Thread.sleep(iSecToWaitLoc * 1000);
        }
        catch (InterruptedException e) {
        }
    }

    public static void refresh(boolean... bRefreshFromServer) {
        waitForAngular();
        boolean bRefreshFromServerLoc = (bRefreshFromServer.length > 0)?bRefreshFromServer[0]:true;
        if (get() instanceof JavascriptExecutor) {
            get().executeScript("document.location.reload("+bRefreshFromServerLoc+");");
            (new WebDriverWait(get(), Global.DEF_TIME_TO_WAIT_PAGE_LOADED_SEC)).until(ExpectedConditions.presenceOfElementLocated(By.tagName("html")));
            waitForAngular();
        } else {
            get().navigate().refresh();
        }
        waitForAngular();
    }

    public void resetBrowser() {
        get().get("about:blank");
    }

    public static boolean hasElement(By by) {
        return !get().findElements(by).isEmpty();
    }
}