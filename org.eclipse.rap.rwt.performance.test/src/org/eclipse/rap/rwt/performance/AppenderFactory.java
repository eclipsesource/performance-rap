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

import org.eclipse.rap.rwt.performance.file.StdOutResultsAppener;


public final class AppenderFactory {

  private static final String APPENDER_PROPERTY
    = AppenderFactory.class.getName();
  private static IResultsAppender appender;

  public static IResultsAppender getAppender() {
    if( appender == null ) {
      try {
        appender = loadAppender();
      } catch( Exception e ) {
        throw new RuntimeException( e );
      }
    }
    return appender;
  }

  private static IResultsAppender loadAppender()
    throws ClassNotFoundException, InstantiationException,
    IllegalAccessException
  {
    IResultsAppender result;
    String appenderClassName = System.getProperty( APPENDER_PROPERTY );
    if( appenderClassName == null ) {
      appenderClassName = StdOutResultsAppener.class.getName();
      Class<?> appenderClass = Class.forName( appenderClassName );
      result = ( IResultsAppender )appenderClass.newInstance();
    } else {
      String[] classNames = appenderClassName.split( "," );
      IResultsAppender[] appenders = new IResultsAppender[ classNames.length ];
      for( int i = 0; i < classNames.length; i++ ) {
        String className = classNames[ i ];
        Class<?> appenderClass = Class.forName( className );
        appenders[ i ] = ( IResultsAppender )appenderClass.newInstance();
      }
      result = new CompositeResultsAppender( appenders );
    }
    return result;
  }
}
