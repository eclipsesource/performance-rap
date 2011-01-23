package org.eclipse.rap.rwt.performance;

import java.util.Arrays;


public class MeasurementResults {

  private final long[] durations;

  public MeasurementResults( long[] durations ) {
    this.durations = durations;
  }

  public int getNumberOfRuns() {
    return durations.length;
  }

  public long getTotalDuration() {
    long result = 0;
    for( long duration : durations ) {
      result += duration;
    }
    return result;
  }

  public long getMinDuration() {
    long result;
    if( durations.length == 0 ) {
      result = 0;
    } else {
      result = Long.MAX_VALUE;
      for( long duration : durations ) {
        result = Math.min( result, duration );
      }
    }
    return result;
  }

  public long getMaxDuration() {
    long result = 0;
    for( long duration : durations ) {
      result = Math.max( result, duration );
    }
    return result;
  }

  public long[] getAllDurations() {
    long[] result = new long[ durations.length ];
    System.arraycopy( durations, 0, result, 0, durations.length );
    return result;
  }

  public long computeAverage() {
    return getTotalDuration() / durations.length;
  }

  public long computeMedian() {
    long result;
    long[] sortedList = durations.clone();
    Arrays.sort( sortedList );
    int median = sortedList.length / 2;
    long rightValue = sortedList[ median ];
    if( sortedList.length % 2 == 1 ) {
      result = rightValue;
    } else {
      long leftValue = sortedList[ median - 1 ];
      result = ( leftValue + rightValue ) / 2;
    }
    return result;
  }
}
