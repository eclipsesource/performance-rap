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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.eclipse.rap.rwt.performance.IResultsAppender;
import org.eclipse.rap.rwt.performance.MeasurementResult;


public class FileResultsAppender implements IResultsAppender {

  private static final String SEPARATOR = ";";
  private final File file;

  public FileResultsAppender( File file ) {
    this.file = file;
  }

  public void append( MeasurementResult results ) {
    Writer out = null;
    try {
      FileWriter writer = new FileWriter( file, true );
      out = new BufferedWriter( writer );
      writeResult( out, results );
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

  private void writeResult( Writer out, MeasurementResult results )
    throws IOException
  {
    String className = results.getTestCaseName();
    String testName = className + "." + results.getTestName();
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

  private void writeResults( Writer out, MeasurementResult results )
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
