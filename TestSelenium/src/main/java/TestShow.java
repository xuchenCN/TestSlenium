import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestShow {

  /**
   * @param args
   * @throws JSONException
   * @throws InterruptedException
   * @throws AWTException
   * @throws IOException
   */
  public static void main(String[] args) throws JSONException,
      InterruptedException, AWTException, IOException {
    
    String[] texts = new String[10];
    texts[0] = "skip non existing resourceDirectory";
    texts[1] = "The script to execute";
    texts[2] = "The target of the script, referenced as arguments[0]";
    texts[3] = "Really should only be used when the web driver is sucking at exposing functionality natively";
    texts[4] = "Using platform encoding";
    texts[5] = " I'm trying to fill out a form in Selenium2. One";
    texts[6] = " WebDriver leaves the focus in the element you called ...";
    texts[7] = "This can upload a file, it is work for me. public static void main(String[] ...";
    texts[8] = "Use this method to simulate typing into an element, which may set ...";
    texts[9] = "Selenium WebDriver - Using sendKeys( ) for entering text into a password field. ARUN MOTOORI·211 videos .";
    

    // 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
    System.setProperty("webdriver.chrome.driver",
        "/Users/chenxu/Downloads/chromedriver");
    // 创建一个 FireFox 的浏览器实例

    final WebDriver driver = new ChromeDriver();

    // driver.manage().window().maximize();
    Runtime.getRuntime().addShutdownHook(new Thread() {

      @Override
      public void run() {
        driver.close();
      }
    });
    // String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
    // 让浏览器访问 Baidu
    
    driver.navigate().to("http://tieba.baidu.com");
    popupCurrnetWindow(driver);

    waitElementDisplay(driver, By.className("u_login"), 2);
    WebElement e = driver.findElement(By.className("u_login"));
    // e.sendKeys(selectLinkOpeninNewTab);
    e.click();
    System.out.println("open login dialog");

    waitElementDisplay(driver, By.id("TANGRAM__PSP_10__unameLoginLink"), 2);
    WebElement useNameLogin = driver.findElement(By
        .id("TANGRAM__PSP_10__unameLoginLink"));
    useNameLogin.click();
    System.out.println("useNameLogin.click()");

     driver.findElement(By.id("TANGRAM__PSP_10__userName")).sendKeys(
     "xuchen198511@gmail.com");
     driver.findElement(By.id("TANGRAM__PSP_10__password"))
     .sendKeys("111222aaa");

//    driver.findElement(By.id("TANGRAM__PSP_10__userName")).sendKeys(
//        "Gigguk");
//    driver.findElement(By.id("TANGRAM__PSP_10__password")).sendKeys(
//        "201330300437");

    driver.findElement(By.id("TANGRAM__PSP_10__submit")).click();
    System.out.println("login");
//
//     waitElementDisplay(driver,By.id("TANGRAM__27__header_a"),2);
//     if(driver.findElement(By.id("TANGRAM__27__header_a")) != null){
//     driver.findElement(By.id("TANGRAM__27__header_a")).click();
//     }

    driver.navigate().to("http://tieba.baidu.com/f?ie=utf-8&kw=剑网3");
//    waitElementDisplay(driver,
//        By.cssSelector("li.j_thread_list.thread_top.clearfix"), 2);

//    List<WebElement> top_topics = driver.findElements(By
//        .cssSelector("li.j_thread_list.thread_top.clearfix"));
//
//    System.out.println(top_topics.size());
//    for (WebElement top_topic : top_topics) {
//      String data_field = top_topic.getAttribute("data-field");
//      JSONObject json = new JSONObject(data_field);
//      System.out.println(json.get("id"));
//      replyTopic(driver, json.getString("id"), "Thanks LZ! Such a good man!!",5);
//    }

    List<WebElement> topics = driver.findElements(By
        .cssSelector("li.j_thread_list.clearfix"));
    System.out.println(topics.size());
    int indexC = 0;
    for (WebElement topic : topics) {
     
      String data_field = topic.getAttribute("data-field");
      JSONObject json = new JSONObject(data_field);
      System.out.println(json.get("id"));
      replyTopic(driver, json.getString("id"), texts,(indexC % 10 ),15000);
      indexC ++;
    }
  }

  public static void waitElementDisplay(WebDriver driver, final By by, long s) {
    (new WebDriverWait(driver, s)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        WebElement e = d.findElement(by);
        return e.isDisplayed();
      }
    });
  }

  public static void chromeNewTabMac() throws AWTException {
    Robot r = new Robot();
    r.keyPress(KeyEvent.VK_CONTROL);
    r.keyPress(KeyEvent.VK_T);
    // r.keyRelease(KeyEvent.VK_CONTROL);
    // r.keyRelease(KeyEvent.VK_T);
  }
  
  public static void paste() throws AWTException{
    Robot r = new Robot();
    r.keyPress(KeyEvent.VK_META);
    r.keyPress(KeyEvent.VK_V);
    r.keyRelease(KeyEvent.VK_V);
    r.keyRelease(KeyEvent.VK_META);
  }

  public static void popupCurrnetWindow(WebDriver driver) {
    String currentWindowHandle = driver.getWindowHandle();

    // run your javascript and alert code
    ((JavascriptExecutor) driver).executeScript("alert('Test')");
    driver.switchTo().alert().accept();

    // Switch back to to the window using the handle saved earlier
    driver.switchTo().window(currentWindowHandle);
  }

  /**
   * Executes a script
   * 
   * @note Really should only be used when the web driver is sucking at exposing
   *       functionality natively
   * @param script
   *          The script to execute
   */
  public static Object trigger(WebDriver driver, String script) {
    return ((JavascriptExecutor) driver).executeScript(script);
  }

  /**
   * Executes a script on an element
   * 
   * @note Really should only be used when the web driver is sucking at exposing
   *       functionality natively
   * @param script
   *          The script to execute
   * @param element
   *          The target of the script, referenced as arguments[0]
   */
  public static void trigger(WebDriver driver, String script, WebElement element) {
    ((JavascriptExecutor) driver).executeScript(script, element);
  }

  public static void openTab(WebDriver driver, String url) {
    
    
    String script = "var d=document,a=d.createElement('a');a.target='_blank';a.href='%s';a.innerHTML='.';d.body.appendChild(a);return a";
    Object element = trigger(driver, String.format(script, url));
    if (element instanceof WebElement) {
      WebElement anchor = (WebElement) element;
      anchor.click();
      trigger(driver, "var a=arguments[0];a.parentNode.removeChild(a);", anchor);
    } else {
      throw new JavaScriptException(element, "Unable to open tab", 1);
    }
  }

  public static void replyTopic(WebDriver driver, String id, String[] content,int index ,long delay)
      throws InterruptedException, AWTException, UnsupportedEncodingException {
    Thread.sleep(1000);
    openTab(driver, "http://tieba.baidu.com/p/" + id);
    
    WebDriver topicWindow = driver.switchTo().window(
        new LinkedList<String>(driver.getWindowHandles()).getLast());
    waitElementDisplay(topicWindow, By.id("ueditor_replace"), 2);
    
    WebElement replyTextArea = topicWindow
        .findElement(By.id("ueditor_replace"));
//    trigger(driver,"document.getElementById('ueditor_replace').innerHTML='<p>测试</p>'");
    
    
//     replyTextArea.sendKeys(content[index]);
//    replyTextArea.sendKeys(content + " timestamp : " + System.currentTimeMillis());
    replyTextArea.click();
    Thread.sleep(1000);
    StringSelection stringSelection = new StringSelection("グーグル");
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
//    replyTextArea.sendKeys();
//    replyTextArea.sendKeys("グーグル");
//    replyTextArea.sendKeys("c");
    replyTextArea.click();
    replyTextArea.sendKeys(Keys.META,"v");
//    replyTextArea.sendKeys(Keys.COMMAND,"v");
//    replyTextArea.sendKeys(Keys.BACK_SPACE);
//    paste();
   
//    topicWindow.findElement(
//        By.cssSelector("a.ui_btn.ui_btn_m.j_submit.poster_submit")).click();
    Thread.sleep(delay);
    topicWindow.close();
    driver.switchTo().window(
        new LinkedList<String>(driver.getWindowHandles()).getLast());
  }

}
