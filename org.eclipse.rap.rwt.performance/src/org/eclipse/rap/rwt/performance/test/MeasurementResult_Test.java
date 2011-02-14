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
package org.eclipse.rap.rwt.performance.test;

import java.util.Arrays;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.MeasurementResult;


public class MeasurementResult_Test extends TestCase {

  public void testCreate() {
    long[] input = new long[ 0 ];
    MeasurementResult result = new MeasurementResult( "foo", "bar", input );
    assertEquals( "foo", result.getTestCaseName() );
    assertEquals( "bar", result.getTestName() );
    assertEquals( 0, result.getNumberOfRuns() );
    assertEquals( 0, result.getTotalDuration() );
    assertEquals( 0, result.getMinDuration() );
    assertEquals( 0, result.getMaxDuration() );
    assertTrue( Arrays.equals( input, result.getAllDurations() ) );
  }

  public void testSafeCopies() {
    long[] input = new long[] { 23 };
    MeasurementResult result = new MeasurementResult( "", "", input );
    long[] allDurations = result.getAllDurations();
    assertNotSame( input, allDurations );
    assertNotSame( allDurations, result.getAllDurations() );
  }

  public void testSingleResult() throws Exception {
    long[] input = new long[] { 23 };
    MeasurementResult result = new MeasurementResult( "", "", input );
    assertEquals( 1, result.getNumberOfRuns() );
    assertEquals( 23, result.getTotalDuration() );
    assertEquals( 23, result.getMinDuration() );
    assertEquals( 23, result.getMaxDuration() );
    assertEquals( 1, result.getAllDurations().length );
    assertEquals( 23, result.getAllDurations()[ 0 ] );
  }

  public void testTwoRunsInResult() throws Exception {
    long[] input = new long[] { 23, 42 };
    MeasurementResult results = new MeasurementResult( "", "", input );
    assertEquals( 2, results.getNumberOfRuns() );
    assertEquals( 23 + 42, results.getTotalDuration() );
    assertEquals( 23, results.getMinDuration() );
    assertEquals( 42, results.getMaxDuration() );
    assertEquals( 2, results.getAllDurations().length );
    assertEquals( 23, results.getAllDurations()[ 0 ] );
    assertEquals( 42, results.getAllDurations()[ 1 ] );
  }

  public void testComputeAverage() {
    long[] input1 = new long[] { 17 };
    assertEquals( 17, computeAverage( input1 ) );
    long[] input2 = new long[] { 4, 8, 4, 8 };
    assertEquals( 6, computeAverage( input2 ) );
  }

  public void testComputeMedian() {
    long[] input1 = new long[] { 17 };
    assertEquals( 17, computeMedian( input1 ) );
    long[] oddInput = new long[] { 23, 42, 1, 17, -12 };
    assertEquals( 17, computeMedian( oddInput ) );
    long[] evenInput = new long[] { 23, 17, 42, 1, -12, 47 };
    assertEquals( 20, computeMedian( evenInput ) );
  }

  private static long computeAverage( long[] input ) {
    MeasurementResult results = new MeasurementResult( "", "", input );
    return results.computeAverage();
  }

  private static long computeMedian( long[] input ) {
    MeasurementResult results = new MeasurementResult( "", "", input );
    return results.computeMedian();
  }
}
