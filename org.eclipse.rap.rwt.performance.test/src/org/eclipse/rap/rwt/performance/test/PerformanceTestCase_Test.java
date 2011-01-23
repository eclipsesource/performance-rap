package org.eclipse.rap.rwt.performance.test;

import org.eclipse.rap.rwt.performance.MeasureRunnable;
import org.eclipse.rap.rwt.performance.PerformanceTestCase;


public class PerformanceTestCase_Test extends PerformanceTestCase {

  public void testMeasuredRun() throws Throwable {
    final StringBuffer buffer = new StringBuffer();
    final MeasureRunnable runnable = new MeasureRunnable() {
      @Override
      public void setUp() {
        buffer.append( "setup." );
      }
      @Override
      public void tearDown() {
        buffer.append( "teardown." );
      }
      public void run() {
        buffer.append( "run." );
      }
    };
    measuredRun( runnable, 3 );
    String expected = "setup.run.teardown."
                    + "setup.run.teardown."
                    + "setup.run.teardown.";
    assertEquals( expected, buffer.toString() );
  }
}
