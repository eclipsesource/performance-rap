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
package org.eclipse.swt.widgets;

import org.eclipse.rap.rwt.performance.MeasureRunnable;
import org.eclipse.rap.rwt.performance.PerformanceTestCase;
import org.eclipse.rwt.Fixture;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.widgets.tablekit.TableLCA;


public class Table_PerformanceTest extends PerformanceTestCase {

  @Override
  protected void setUp() throws Exception {
    Fixture.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    Fixture.tearDown();
  }

  public void testRemoveAll() throws Exception {
    Display display = new Display();
    Shell shell = new Shell( display );
    final Table table = new Table( shell, SWT.SINGLE | SWT.FULL_SELECTION );
    MeasureRunnable testable = new MeasureRunnable() {

      @Override
      public void setUp() {
        for( int i = 0; i < 50; i++ ) {
          TableItem tableItem = new TableItem( table, SWT.NONE );
          tableItem.setText( "foo " + i );
        }
      }

      public void run() {
        table.removeAll();
      }
    };
    measuredRun( testable, 1000 );
  }

  public void testComputeSize() throws Exception {
    Display display = new Display();
    Shell shell = new Shell( display );
    final Table table = new Table( shell, SWT.SINGLE | SWT.FULL_SELECTION );
    TableItem tableItem = new TableItem( table, SWT.NONE );
    tableItem.setText( "foo " );
    MeasureRunnable testable = new MeasureRunnable() {
      public void run() {
        table.computeSize( SWT.DEFAULT, SWT.DEFAULT );
      }
    };
    measuredRun( testable, 1000 );
  }

  public void testSelectAll() throws Exception {
    Display display = new Display();
    Shell shell = new Shell( display );
    final Table table = new Table( shell, SWT.SINGLE | SWT.FULL_SELECTION );
    for( int i = 0; i < 1000; i++ ) {
      TableItem tableItem = new TableItem( table, SWT.NONE );
      tableItem.setText( "foo " );
    }
    MeasureRunnable testable = new MeasureRunnable() {
    
      @Override
      public void setUp() {
        table.deselectAll();
      }
    
      public void run() {
        table.selectAll();
      }
    };
    measuredRun( testable, 10 );
  }

  public void testLCAPerformance() throws Exception {
    Display display = new Display();
    Shell shell = new Shell( display );
    final Table table = new Table( shell, SWT.SINGLE | SWT.FULL_SELECTION );
    for( int i = 0; i < 100; i++ ) {
      TableItem tableItem = new TableItem( table, SWT.NONE );
      tableItem.setText( "foo " );
    }
    final TableLCA lca = new TableLCA();
    Fixture.fakeResponseWriter();
    MeasureRunnable testable = new MeasureRunnable() {
      public void run() {
        try {
          lca.render( table );
        } catch( Exception e ) {
          e.printStackTrace();
          fail( e.getMessage() );
        }
      }
    };
    measuredRun( testable, 100 );
  }
}
