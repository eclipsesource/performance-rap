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

import junit.framework.TestCase;


public class PerformanceTestCase extends TestCase {

  public void measuredRun( final MeasureRunnable testable, final int times ) {
    StopWatch watch = new StopWatch();
    for( int i = 0; i < times; i++ ) {
      testable.setUp();
      watch.start();
      testable.run();
      watch.stop();
      testable.tearDown();
    }
    MeasurementResult results = createResults( watch.getDurations() );
    IResultsAppender appender = AppenderFactory.getAppender();
    appender.append( results );
  }

  private MeasurementResult createResults( final long[] durations ) {
    String testCaseName = getClass().getName();
    String testName = getName();
    MeasurementResult result = new MeasurementResult( testCaseName,
                                                      testName,
                                                      durations );
    return result;
  }
}
