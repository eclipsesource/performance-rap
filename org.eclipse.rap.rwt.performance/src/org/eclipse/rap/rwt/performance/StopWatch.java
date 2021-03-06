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

import java.util.ArrayList;
import java.util.List;


/**
 * Stopwatch for time measurements.
 * <p>
 * <strong>Note:</strong> this class is <em>not</em> thread-safe. Callers must
 * ensure that instances of this class are called by only one thread.
 * </p>
 */
public final class StopWatch {

  private final List<Long> frames;
  private long startTime;
  private boolean running = false;

  public StopWatch() {
    frames = new ArrayList<Long>();
  }

  public void start() {
    if( running ) {
      throw new IllegalStateException( "Stopwatch already running" );
    }
    running = true;
    startTime = System.nanoTime();
  }

  public void stop() {
    long endTime = System.nanoTime();
    if( !running ) {
      throw new IllegalStateException( "Stopwatch not running" );
    }
    long duration = endTime - startTime;
    frames.add( Long.valueOf( duration ) );
    running = false;
  }

  public long[] getDurations() {
    long[] durations = new long[ frames.size() ];
    for( int i = 0; i < durations.length; i++ ) {
      Long frame = frames.get( i );
      durations[ i ] = frame.longValue();
    }
    return durations;
  }
}
