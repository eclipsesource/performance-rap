package org.eclipse.rap.rwt.performance;

import org.eclipse.rwt.Fixture;

import junit.framework.TestCase;


public class PerformanceTestCase extends TestCase {

  private StopWatch watch;

  protected void setUp() throws Exception {
    Fixture.setUp();
    watch = new StopWatch();
  }

  protected void tearDown() throws Exception {
    Fixture.tearDown();
  }

  public void measuredRun( final MeasureRunnable testable, final int times ) {
    for( int i = 0; i < times; i++ ) {
      testable.setUp();
      watch.start();
      testable.run();
      watch.stop();
      testable.tearDown();
    }
    StopWatchResults results = watch.getResults();
    Logger.printResults( results );
    IPerformanceStorage storage = StorageFactory.createPerformanceStorage();
    storage.putResults( this, results.getAllDurations() );
  }

  public void assertPerformance() {
    IPerformanceStorage storage = StorageFactory.createPerformanceStorage();
    storage.getAggregatedResults();
  }
}
