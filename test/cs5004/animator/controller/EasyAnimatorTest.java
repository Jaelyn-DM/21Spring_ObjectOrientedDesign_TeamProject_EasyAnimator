package cs5004.animator.controller;

import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import cs5004.animator.model.IAnimation;
import cs5004.animator.view.IView;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for Easy Animator.
 */
public class EasyAnimatorTest {
  private MockController controller;
  private IView view;
  private Readable in;
  private PrintStream out;
  private StringBuilder log;
  @Before
  public void setUp() throws Exception {
    IAnimation model = new MockModel(log, 123);
    this.view = new MockView(log, 456);
    this.controller = new MockController();
  }
  @Test
  public void testMain() throws Exception {
    String[] x = new String[8];
    x[0] = "-in";
    x[1] = "code/smalldemo.txt";
    x[2] = "-speed";
    x[3] = "30";
    x[4] = "-view";
    x[5] = "text";
    x[6] = "-out";
    x[7] = "test.txt";
    controller.main(x);
    assertEquals(456, view.getSpeed());
    assertTrue(view instanceof MockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeTestMain1() throws Exception {
    controller.main(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeTestMain2() throws Exception {
    String[] x = new String[]{"-speed", "2", "-view", "text", "-out", "test.txt"};
    controller.main(x);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeTestMain3() throws Exception {
    String[] x = new String[8];
    x[0] = "-in";
    x[1] = "code/smalldemo.txt";
    x[2] = "-speed";
    x[3] = "-10";
    x[4] = "-view";
    x[5] = "text";
    x[6] = "-out";
    x[7] = "test.txt";
    controller.main(x);
  }

  @Test(expected = FileNotFoundException.class)
  public void negativeTestMain4() throws Exception {
    String[] x = new String[8];
    x[0] = "-in";
    x[1] = "smalldemo";
    x[2] = "-speed";
    x[3] = "-10";
    x[4] = "-view";
    x[5] = "text";
    x[6] = "-out";
    x[7] = "test.txt";
    controller.main(x);
  }
}