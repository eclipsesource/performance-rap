package org.eclipse.rap.rwt.performance.file;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.IResultsAppender;
import org.eclipse.rap.rwt.performance.MeasurementResults;


public class StdOutResultsAppener implements IResultsAppender {

  private static boolean headerPrinted;
    
  public StdOutResultsAppener() {
    headerPrinted = false;
  }

  public void append( final TestCase test, final MeasurementResults results ) {
    if( results == null ) {
      throw new NullPointerException( "results" );
    }
    if( !headerPrinted ) {
      printHeader();
    }
    printResult( getTestName( test ), results );
  }

  public void dispose() throws Exception {
    // nothing to do
  }

  private String getTestName( final TestCase test ) {
    String className = test.getClass().getName();
    int lastDot = className.lastIndexOf( '.' );
    if( lastDot != -1 ) {
      className = className.substring( lastDot + 1 );
    }
    String testName = className + "." + test.getName();
    return testName;
  }

  private static void printHeader() {
    System.out.println( "Testcase\tAvg (ms)\tMed (ms)" );
    headerPrinted = true;
  }

  private static void printResult( final String name,
                                   final MeasurementResults results )
  {
    System.out.print( name );
    System.out.print( "\t" );
    System.out.print( formatTime( results.computeAverage() ) );
    System.out.print( "\t" );
    System.out.print( formatTime( results.computeMedian() ) );
    System.out.println();
  }

  private static String formatTime( final long time ) {
    long millis = time / 1000 / 1000;
    long micros = ( time / 1000 ) % 1000;
    StringBuilder buffer = new StringBuilder();
    buffer.append( millis );
    buffer.append( "." );
    if( micros < 10 ) {
      buffer.append( "00" );
    } else if( micros < 100 ) {
      buffer.append( "0" );
    }
    buffer.append( micros );
    while( buffer.charAt( buffer.length() - 1 ) == '0'
           && buffer.charAt( buffer.length() - 2 ) != '.' )
    {      
      buffer.setLength( buffer.length() - 1 );
    }
    return buffer.toString();
  }
}
