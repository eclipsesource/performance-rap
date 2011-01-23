package org.eclipse.rap.rwt.performance;

import org.eclipse.rwt.Fixture;

import junit.framework.TestCase;


public class PerformanceTestCase extends TestCase {

  @Override
  protected void setUp() throws Exception {
    Fixture.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    Fixture.tearDown();
  }

  public void measuredRun( final MeasureRunnable testable, final int times ) {
    StopWatch watch = new StopWatch();
    for( int i = 0; i < times; i++ ) {
      testable.setUp();
      watch.start();
      testable.run();
      watch.stop();
      testable.tearDown();
    }
    IResultsAppender storage = AppenderFactory.getAppender();
    storage.append( this, watch.getResults() );
  }
}
