package org.eclipse.rap.rwt.performance;

import junit.framework.TestCase;


/**
 * Appender for test results.
 * <p>
 * Instances will be created by the {@link AppenderFactory}. Each storage is
 * responsible to dispose acquired resources upon {@link #dispose()}.
 * </p>
 */
public interface IResultsAppender {

  /**
   * Appends the given results to this appender, grouped by the given testcase.
   * 
   * @param test the current testcase
   * @param results the results to append
   */
  void append( TestCase test, MeasurementResults results );

  /**
   * Releases all resources that this appender may have been acquired such as
   * file locks or database connections.
   * 
   * @throws Exception
   */
  void dispose() throws Exception;

}
