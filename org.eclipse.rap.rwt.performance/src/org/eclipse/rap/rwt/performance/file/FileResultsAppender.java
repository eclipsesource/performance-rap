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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.IResultsAppender;
import org.eclipse.rap.rwt.performance.MeasurementResults;


public class FileResultsAppender implements IResultsAppender {

  private static final String SEPARATOR = ";";
  private String FILE_NAME = System.getProperty( "user.home" ) + "/results.csv";

  public void append( TestCase test, MeasurementResults results ) {
    Writer out = null;
    try {
      FileWriter writer = new FileWriter( FILE_NAME, true );
      out = new BufferedWriter( writer );
      writeResult( out, test, results );
    } catch( Exception e ) {
      throw new RuntimeException( e );
    } finally {
      try {
        if( out != null ) {
          out.close();
        }
      } catch( IOException e ) {
        throw new RuntimeException( e );
      }
    }
  }

  public void dispose() throws Exception {
    // nothing to do
  }

  private void writeResult( Writer out, TestCase test, MeasurementResults results )
    throws IOException
  {
    String className = test.getClass().getName();
    String testName = className + "." + test.getName();
    writeTestname( out, testName );
    writeSeparator( out );
    writeResults( out, results );
    out.write( "\n" );
  }

  private void writeTestname( Writer out, String testName ) throws IOException {
    out.write( testName );
  }

  private void writeSeparator( Writer out ) throws IOException {
    out.write( SEPARATOR );
  }

  private void writeResults( Writer out, MeasurementResults results )
    throws IOException
  {
    long[] durations = results.getAllDurations();
    for( int i = 0; i < durations.length; i++ ) {
      long frameTime = durations[ i ];
      out.write( String.valueOf( getTimeInMilliSeconds( frameTime ) ) );
      writeSeparator( out );
    }
  }

  private long getTimeInMilliSeconds( long frameTime ) {
    return frameTime / 1000 / 1000;
  }
}
