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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.MeasurementResult;
import org.eclipse.rap.rwt.performance.file.StdOutResultsAppener;


public class StdOutResultsAppender_Test extends TestCase {

  private PrintStream bufferedSysOut;
  private PrintStream bufferedSysErr;
  private ByteArrayOutputStream sysOut;
  private ByteArrayOutputStream sysErr;

  @Override
  protected void setUp() throws Exception {
    bufferedSysOut = System.out;
    bufferedSysErr = System.err;
    sysOut = new ByteArrayOutputStream();
    sysErr = new ByteArrayOutputStream();
    System.setOut( new PrintStream( sysOut ) );
    System.setErr( new PrintStream( sysErr ) );
  }

  @Override
  protected void tearDown() throws Exception {
    System.setOut( bufferedSysOut );
    System.setErr( bufferedSysErr );
  }

  public void testAppendNull() {
    StdOutResultsAppener appender = new StdOutResultsAppener();
    try {
      appender.append( (MeasurementResult)null );
      fail();
    } catch( NullPointerException e ) {
      // expected
    }
  }

  public void testAppendEmptyResult() {
    StdOutResultsAppener appender = new StdOutResultsAppener();
    MeasurementResult results = new MeasurementResult( "", "", new long[] { 0L } );
    appender.append( results );
    String expected = "Testcase\tAvg (ms)\tMed (ms)\n"
                      + "StdOutResultsAppender_Test.testAppendEmptyResult\t"
                      + "0.0\t0.0\n";
    assertEquals( expected, sysOut.toString() );
    assertEquals( "", sysErr.toString() );
  }

  public void testAppendTwoResults() {
    StdOutResultsAppener appender = new StdOutResultsAppener();
    long[] input1 = new long[] { 22000L, 55000L, 22000L };
    appender.append( new MeasurementResult( "", "", input1 ) );
    long[] input2 = new long[] { 20000L, 50000L, 20000L };
    appender.append( new MeasurementResult( "", "", input2 ) );
    String expected = "Testcase\tAvg (ms)\tMed (ms)\n"
                      + "StdOutResultsAppender_Test.testAppendTwoResults\t"
                      + "0.033\t0.022\n"
                      + "StdOutResultsAppender_Test.testAppendTwoResults\t"
                      + "0.03\t0.02\n";
    assertEquals( expected, sysOut.toString() );
    assertEquals( "", sysErr.toString() );
  }
}
