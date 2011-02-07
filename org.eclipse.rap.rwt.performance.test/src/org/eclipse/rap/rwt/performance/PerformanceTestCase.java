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

import org.eclipse.rwt.Fixture;

import junit.framework.TestCase;


public class PerformanceTestCase extends TestCase {

  @Override
  protected void setUp() throws Exception {
    Fixture.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    Fixture.tearDown();
  }

  public void measuredRun( final MeasureRunnable testable, final int times ) {
    StopWatch watch = new StopWatch();
    for( int i = 0; i < times; i++ ) {
      testable.setUp();
      watch.start();
      testable.run();
      watch.stop();
      testable.tearDown();
    }
    IResultsAppender storage = AppenderFactory.getAppender();
    storage.append( this, watch.getResults() );
  }
}
