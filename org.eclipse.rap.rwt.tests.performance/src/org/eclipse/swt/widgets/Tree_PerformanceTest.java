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
import org.eclipse.swt.SWT;

public class Tree_PerformanceTest extends PerformanceTestCase {

  public void testFlatIndex() throws Exception {
    Display display = new Display();
    Shell shell = new Shell( display );
    final Tree tree = new Tree( shell, SWT.SINGLE | SWT.FULL_SELECTION );
    for( int i = 0; i < 200; i++ ) {
      TreeItem treeItem = new TreeItem( tree, SWT.NONE );
      treeItem.setText( "foo " + i );
      treeItem.setExpanded( true );
      for( int j = 0; j < 10; j++ ) {
        TreeItem subTreeItem = new TreeItem( tree, SWT.NONE );
        subTreeItem.setText( "sub " + j );
        subTreeItem.setExpanded( true );
      }
    }
    TreeItem treeItem = new TreeItem( tree, SWT.NONE );
    treeItem.setText( "last item" );
    MeasureRunnable testable = new MeasureRunnable() {
    
      public void run() {
        tree.updateFlatIndices();
      }
    };
    measuredRun( testable, 1000 );
  }
}
