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
import org.eclipse.swt.graphics.Rectangle;

public class ToolItem_PerformanceTest extends PerformanceTestCase {

  @Override
  protected void setUp() throws Exception {
    Fixture.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    Fixture.tearDown();
  }

  public void testBounds() throws Exception {
    Display display = new Display();
    Shell shell = new Shell( display );
    
    ToolBar toolBar = new ToolBar( shell, SWT.FLAT );
    final ToolItem toolItem = new ToolItem( toolBar, SWT.PUSH );
    toolItem.setText("foo");
    
    final Rectangle[] bounds = new Rectangle[1];
    MeasureRunnable testable = new MeasureRunnable() {
      public void run() {
        bounds[0] = toolItem.getBounds();
      }
    };
    measuredRun( testable, 10000 );
  }

}
