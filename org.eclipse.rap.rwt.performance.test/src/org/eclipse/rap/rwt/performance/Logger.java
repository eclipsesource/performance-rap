package org.eclipse.rap.rwt.performance;


// TODO [rst] Transient class to hold extracted logging code
public final class Logger {
  
  public static void printResults( final long[] results ) {
    long sum = 0;
    long min = Integer.MAX_VALUE;
    long max = 0;
    for( int i = 0; i < results.length; i++ ) {
      long duration = results[ i ];
      sum = sum + duration;
      min = Math.min( min, duration );
      max = Math.max( max, duration );
    }
    System.out.println( "Average Time: " + formatTime( sum / results.length ) );
    System.out.println( "Min Time: " + formatTime( min ) );
    System.out.println( "Max Time: " + formatTime( max ) );
    System.out.println( "Total time: " + formatTime( sum ) );
  }

  private static String formatTime( long time ) {
    long microSeconds = time / 1000;
    long milliSeconds = microSeconds / 1000;
    return milliSeconds + "ms";
  }
}
