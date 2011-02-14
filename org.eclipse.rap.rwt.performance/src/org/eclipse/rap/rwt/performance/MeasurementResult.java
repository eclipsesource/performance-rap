/*******************************************************************************
 * Copyright (c) 2010, 2011 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.rwt.performance;

import java.util.Arrays;


public class MeasurementResult {

  private final String testCaseName;
  private final String testName;
  private final long[] durations;

  public MeasurementResult( final String testCaseName,
                            final String testName,
                            final long[] durations )
  {
    this.testCaseName = testCaseName;
    this.testName = testName;
    this.durations = durations;
  }

  public String getTestCaseName() {
    return testCaseName;
  }

  public String getTestName() {
    return testName;
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
