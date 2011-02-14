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

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.StopWatch;


public class StopWatch_Test extends TestCase {

  public void testCreate() {
    StopWatch stopWatch = new StopWatch();
    long[] results = stopWatch.getDurations();
    assertEquals( 0, results.length );
  }

  public void testRunOnce() throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Thread.sleep( 1 );
    stopWatch.stop();
    long[] results = stopWatch.getDurations();
    assertEquals( 1, results.length );
    assertTrue( results[ 0 ] > 0 );
  }

  public void testRunTwice() throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Thread.sleep( 1 );
    stopWatch.stop();
    stopWatch.start();
    Thread.sleep( 2 );
    stopWatch.stop();
    long[] results = stopWatch.getDurations();
    assertEquals( 2, results.length );
    assertTrue( results[ 0 ] > 0 );
    assertTrue( results[ 1 ] > 0 );
    assertTrue( results[ 0 ] != results[ 1 ] );
  }

  public void testStartTwice() {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    try {
      stopWatch.start();
      fail( "StopWatch already running" );
    } catch( IllegalStateException e ) {
      // expected
    }
  }

  public void testStopWithoutStarting() {
    StopWatch stopWatch = new StopWatch();
    try {
      stopWatch.stop();
      fail( "StopWatch not running" );
    } catch( IllegalStateException e ) {
      // expected
    }
  }
}
