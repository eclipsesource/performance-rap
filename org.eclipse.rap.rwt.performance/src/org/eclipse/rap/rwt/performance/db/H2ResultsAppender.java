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
package org.eclipse.rap.rwt.performance.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.rap.rwt.performance.IResultsAppender;
import org.eclipse.rap.rwt.performance.MeasurementResult;


public class H2ResultsAppender implements IResultsAppender {

  private static final String JDBC_URI = "jdbc:h2:tcp://localhost/~/performance";
  private static final String USERNAME = "sa";
  private static final String PASSWORD = "";
  private Connection con;

  public H2ResultsAppender() {
    try {
      Class.forName( "org.h2.Driver" );
    } catch( ClassNotFoundException e ) {
      e.printStackTrace();
    }
  }

  public void append( MeasurementResult results ) {
    int testId = putTest( results );
    int runId = putTestRun( testId );
    putFrames( runId, results.getAllDurations() );
  }
  
  public void dispose() throws Exception {
    if( con != null ) {
      con.close();
    }
  }

  private void putFrames( int runId, long[] frames ) {
    for( int i = 0; i < frames.length; i++ ) {
      long frameTime = frames[ i ];
      putFrame( runId, frameTime );
    }
  }

  private void putFrame( int runId, long frameTime ) {
    try {
      Connection con = getConnection();
      String sql = "INSERT INTO iterations( TESTRUNID, RESULT) VALUES (?, ?);";
      PreparedStatement stmt = con.prepareStatement( sql );
      stmt.setInt( 1, runId );
      stmt.setLong( 2, frameTime );
      stmt.execute();
    } catch( SQLException e ) {
      e.printStackTrace();
    }
  }

  private int putTestRun( int testId ) {
    Connection con = getConnection();
    int runId = 0;
    try {
      String sql = "INSERT INTO testruns( TESTID , DATE) VALUES (?, NOW());";
      PreparedStatement stmt = con.prepareStatement( sql );
      stmt.setInt( 1, testId );
      stmt.execute();
      runId = getLastInsertId( stmt );
    } catch( SQLException e ) {
      e.printStackTrace();
    }
    return runId;
  }

  private int putTest( MeasurementResult results ) {
    Connection con = getConnection();
    String className = results.getTestCaseName();
    String testName = results.getTestName();
    int testId = 0;
    try {
      String sql = "INSERT INTO tests( TESTNAME , TESTCLASS) VALUES (?, ?);";
      PreparedStatement stmt = con.prepareStatement( sql );
      stmt.setString( 1, testName );
      stmt.setString( 2, className );
      stmt.execute();
      testId = getLastInsertId( stmt );
    } catch( SQLException e ) {
      e.printStackTrace();
    }
    return testId;
  }

  private int getLastInsertId( PreparedStatement stmt ) throws SQLException {
    ResultSet keys = stmt.getGeneratedKeys();
    keys.next();
    return keys.getInt( 1 );
  }

  private Connection getConnection() {
    Connection result = null;
    if( con != null ) {
      result = con;
    } else {
      try {
        result = DriverManager.getConnection( JDBC_URI, USERNAME, PASSWORD );
      } catch( SQLException e ) {
        e.printStackTrace();
      }
    }
    return result;
  }
}
