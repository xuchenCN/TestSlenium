import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;


public class TestCopy {

  /**
   * @param args
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    Thread.sleep(3000);
    Robot r = new Robot();
//    r.keyPress(KeyEvent.VK_META);
    r.keyPress(KeyEvent.VK_4);
    r.keyRelease(KeyEvent.VK_4);
//    r.keyRelease(KeyEvent.VK_META);
    

  }

}
