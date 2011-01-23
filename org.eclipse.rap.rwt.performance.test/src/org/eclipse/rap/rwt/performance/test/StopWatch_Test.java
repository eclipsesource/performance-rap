package org.eclipse.rap.rwt.performance.test;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.StopWatch;
import org.eclipse.rap.rwt.performance.StopWatchResults;


public class StopWatch_Test extends TestCase {

  public void testCreate() {
    StopWatch stopWatch = new StopWatch();
    StopWatchResults results = stopWatch.getResults();
    assertEquals( 0, results.getNumberOfRuns() );
    assertEquals( 0, results.getTotalDuration() );
    assertEquals( 0, results.getMinDuration() );
    assertEquals( 0, results.getMaxDuration() );
    assertEquals( 0, results.getAllDurations().length );
  }

  public void testRunOnce() throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Thread.sleep( 1 );
    stopWatch.stop();
    StopWatchResults results = stopWatch.getResults();
    assertEquals( 1, results.getNumberOfRuns() );
    long totalDuration = results.getTotalDuration();
    assertTrue( totalDuration > 0 );
    assertEquals( totalDuration, results.getMinDuration() );
    assertEquals( totalDuration, results.getMaxDuration() );
    assertEquals( 1, results.getAllDurations().length );
    assertEquals( totalDuration, results.getAllDurations()[ 0 ] );
  }

  public void testRunTwice() throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Thread.sleep( 1 );
    stopWatch.stop();
    stopWatch.start();
    Thread.sleep( 2 );
    stopWatch.stop();
    StopWatchResults results = stopWatch.getResults();
    assertEquals( 2, results.getNumberOfRuns() );
    long totalDuration = results.getTotalDuration();
    long minDuration = results.getMinDuration();
    long maxDuration = results.getMaxDuration();
    assertTrue( 0 < minDuration );
    assertTrue( minDuration < maxDuration );
    assertEquals( minDuration + maxDuration, totalDuration );
    assertEquals( 2, results.getAllDurations().length );
    assertEquals( minDuration, results.getAllDurations()[ 0 ] );
    assertEquals( maxDuration, results.getAllDurations()[ 1 ] );
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