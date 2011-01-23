package org.eclipse.rap.rwt.performance.test;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.IPerformanceStorage;
import org.eclipse.rap.rwt.performance.StorageFactory;
import org.eclipse.rap.rwt.performance.file.StdOutPerformanceStorage;


public class StorageFactory_Test extends TestCase {

  public void testDefault() {
    IPerformanceStorage storage = StorageFactory.createPerformanceStorage();
    assertTrue( storage instanceof StdOutPerformanceStorage );
  }

  public void testSameInstance() {
    IPerformanceStorage storage = StorageFactory.createPerformanceStorage();
    assertSame( storage, StorageFactory.createPerformanceStorage() );
  }
}
