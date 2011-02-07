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


public class CompositeResultsAppender implements IResultsAppender {

  private IResultsAppender[] appenders;

  public CompositeResultsAppender( final IResultsAppender[] appenders ) {
    this.appenders = appenders;
  }

  public void append( final TestCase test, final MeasurementResults results ) {
    for( int i = 0; i < appenders.length; i++ ) {
      IResultsAppender appender = appenders[ i ];
      appender.append( test, results );
    }
  }

  public void dispose() throws Exception {
    for( int i = 0; i < appenders.length; i++ ) {
      IResultsAppender appender = appenders[ i ];
      appender.dispose();
    }
  }
}
