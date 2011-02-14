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
package org.eclipse.rap.rwt.performance.file;

import org.eclipse.rap.rwt.performance.IResultsAppender;
import org.eclipse.rap.rwt.performance.MeasurementResult;


public class StdOutResultsAppener implements IResultsAppender {

  private static boolean headerPrinted;

  public StdOutResultsAppener() {
    headerPrinted = false;
  }

  public void append( final MeasurementResult results ) {
    if( results == null ) {
      throw new NullPointerException( "results" );
    }
    if( !headerPrinted ) {
      printHeader();
    }
    printResult( getTestName( results ), results );
  }

  public void dispose() throws Exception {
    // nothing to do
  }

  private String getTestName( final MeasurementResult results ) {
    String className = results.getTestCaseName();
    int lastDot = className.lastIndexOf( '.' );
    if( lastDot != -1 ) {
      className = className.substring( lastDot + 1 );
    }
    return className + "." + results.getTestName();
  }

  private static void printHeader() {
    System.out.println( "Testcase\tAvg (ms)\tMed (ms)" );
    headerPrinted = true;
  }

  private static void printResult( final String name,
                                   final MeasurementResult results )
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
