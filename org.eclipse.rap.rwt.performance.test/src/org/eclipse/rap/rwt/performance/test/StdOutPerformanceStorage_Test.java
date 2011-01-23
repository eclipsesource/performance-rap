package org.eclipse.rap.rwt.performance.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.MeasurementResults;
import org.eclipse.rap.rwt.performance.file.StdOutResultsAppener;


public class StdOutPerformanceStorage_Test extends TestCase {

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
      appender.append( this, (MeasurementResults)null );
      fail();
    } catch( NullPointerException e ) {
      // expected
    }
  }

  public void testAppendEmptyResult() {
    StdOutResultsAppener appender = new StdOutResultsAppener();
    MeasurementResults results = new MeasurementResults( new long[] { 0L } );
    appender.append( this, results );
    String expected = "Testcase\tAvg (ms)\tMed (ms)\n"
                      + "StdOutPerformanceStorage_Test.testAppendEmptyResult\t"
                      + "0.0\t0.0\n";
    assertEquals( expected, sysOut.toString() );
    assertEquals( "", sysErr.toString() );
  }

  public void testAppendTwoResults() {
    StdOutResultsAppener appender = new StdOutResultsAppener();
    long[] input1 = new long[] { 22000L, 55000L, 22000L };
    appender.append( this, new MeasurementResults( input1 ) );
    long[] input2 = new long[] { 20000L, 50000L, 20000L };
    appender.append( this, new MeasurementResults( input2 ) );
    String expected = "Testcase\tAvg (ms)\tMed (ms)\n"
                      + "StdOutPerformanceStorage_Test.testAppendTwoResults\t"
                      + "0.033\t0.022\n"
                      + "StdOutPerformanceStorage_Test.testAppendTwoResults\t"
                      + "0.03\t0.02\n";
    assertEquals( expected, sysOut.toString() );
    assertEquals( "", sysErr.toString() );
  }
}
