package org.eclipse.rap.rwt.performance.test;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.StopWatch;
import org.eclipse.rap.rwt.performance.MeasurementResults;


public class StopWatch_Test extends TestCase {

  public void testCreate() {
    StopWatch stopWatch = new StopWatch();
    MeasurementResults results = stopWatch.getResults();
    assertEquals( 0, results.getNumberOfRuns() );
    assertEquals( 0, results.getAllDurations().length );
  }

  public void testRunOnce() throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Thread.sleep( 1 );
    stopWatch.stop();
    MeasurementResults results = stopWatch.getResults();
    assertEquals( 1, results.getNumberOfRuns() );
    assertEquals( 1, results.getAllDurations().length );
    assertTrue( results.getAllDurations()[ 0 ] > 0 );
  }

  public void testRunTwice() throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Thread.sleep( 1 );
    stopWatch.stop();
    stopWatch.start();
    Thread.sleep( 2 );
    stopWatch.stop();
    MeasurementResults results = stopWatch.getResults();
    assertEquals( 2, results.getNumberOfRuns() );
    long[] allDurations = results.getAllDurations();
    assertEquals( 2, allDurations.length );
    assertTrue( allDurations[ 0 ] > 0 );
    assertTrue( allDurations[ 1 ] > 0 );
    assertTrue( allDurations[ 0 ] != allDurations[ 1 ] );
  }

  public void testStartTwice() {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    try {
      stopWatch.start();
      fail( "StopWatch already running" );
    } catch( IllegalStateException e ) {
      // expected
    }
  }

  public void testStopWithoutStarting() {
    StopWatch stopWatch = new StopWatch();
    try {
      stopWatch.stop();
      fail( "StopWatch not running" );
    } catch( IllegalStateException e ) {
      // expected
    }
  }
}
