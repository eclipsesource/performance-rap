package org.eclipse.rap.rwt.performance.test;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.IResultsAppender;
import org.eclipse.rap.rwt.performance.AppenderFactory;
import org.eclipse.rap.rwt.performance.file.StdOutResultsAppener;


public class StorageFactory_Test extends TestCase {

  public void testDefault() {
    IResultsAppender appender = AppenderFactory.getAppender();
    assertTrue( appender instanceof StdOutResultsAppener );
  }

  public void testSameInstance() {
    IResultsAppender appender = AppenderFactory.getAppender();
    assertSame( appender, AppenderFactory.getAppender() );
  }
}
