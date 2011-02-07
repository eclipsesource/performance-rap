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
package org.eclipse.rwt.internal.resources;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.rwt.performance.MeasureRunnable;
import org.eclipse.rap.rwt.performance.PerformanceTestCase;

public class ResourceUtil_PerformanceTest extends PerformanceTestCase {

  public void testReadBinary() throws Exception {
    MeasureRunnable testable = new MeasureRunnable() {
    
      public void run() {
        ClassLoader classLoader = getClass().getClassLoader();
        String imageFile = "resources/big_binary.png";
        InputStream stream = classLoader.getResourceAsStream( imageFile );
        try {
          ResourceUtil.readBinary( stream );
        } catch( IOException e ) {
          e.printStackTrace();
          fail();
        }
      }
    };
    measuredRun( testable, 100 );
  }
}
