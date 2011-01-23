package org.eclipse.rap.rwt.performance;

import junit.framework.TestCase;


public class CompositeResultsAppender implements IResultsAppender {

  private IResultsAppender[] internalAppenders;

  public CompositeResultsAppender( final IResultsAppender[] appenders ) {
    internalAppenders = appenders;
  }

  public void append( final TestCase test, final MeasurementResults results ) {
    for( int i = 0; i < internalAppenders.length; i++ ) {
      IResultsAppender storage = internalAppenders[ i ];
      storage.append( test, results );
    }
  }

  public void dispose() throws Exception {
    for( int i = 0; i < internalAppenders.length; i++ ) {
      IResultsAppender storage = internalAppenders[ i ];
      storage.dispose();
    }
  }
}
