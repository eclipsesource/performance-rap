package org.eclipse.rap.rwt.performance;


// TODO [rst] Transient class to hold extracted logging code
public final class Logger {

  public static void printResults( final StopWatchResults results ) {
    long totalDuration = results.getTotalDuration();
    long minDuration = results.getMinDuration();
    long maxDuration = results.getMaxDuration();
    long avgDuration = totalDuration / results.getNumberOfRuns();
    System.out.println( "Average Time: " + formatTime( avgDuration ) );
    System.out.println( "Min Time: " + formatTime( minDuration ) );
    System.out.println( "Max Time: " + formatTime( maxDuration ) );
    System.out.println( "Total time: " + formatTime( totalDuration ) );
  }

  private static String formatTime( long time ) {
    long microSeconds = time / 1000;
    long milliSeconds = microSeconds / 1000;
    return milliSeconds + "ms";
  }
}
