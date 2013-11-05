import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class TestKey extends JFrame implements KeyListener {

  public TestKey() {
    setVisible(true);
    addKeyListener(this);
    new Thread() {
      public void run() {
        for (;;) {
          try {
            sleep(50);
          } catch (InterruptedException e) {
            break;
          }
        }
      }
    }.start();
  }

  public static void main(String[] args) {
    new TestKey();
  }

  public void keyPressed(KeyEvent e) {
    System.out.println(e);
  }

  public void keyReleased(KeyEvent e) {
  }

  public void keyTyped(KeyEvent e) {
  }
}
