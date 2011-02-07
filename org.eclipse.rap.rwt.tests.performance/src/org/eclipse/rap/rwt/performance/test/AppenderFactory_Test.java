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

import org.eclipse.rap.rwt.performance.IResultsAppender;
import org.eclipse.rap.rwt.performance.AppenderFactory;
import org.eclipse.rap.rwt.performance.file.StdOutResultsAppener;


public class AppenderFactory_Test extends TestCase {

  public void testDefault() {
    IResultsAppender appender = AppenderFactory.getAppender();
    assertTrue( appender instanceof StdOutResultsAppener );
  }

  public void testSameInstance() {
    IResultsAppender appender = AppenderFactory.getAppender();
    assertSame( appender, AppenderFactory.getAppender() );
  }
}
