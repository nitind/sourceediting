/*******************************************************************************
 * Copyright (c) 2008 Chase Technology Ltd - http://www.chasetechnology.co.uk
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Doug Satchwell (Chase Technology Ltd) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xsl.core.tests;


import org.eclipse.wst.xsl.internal.core.tests.TestIncludedTemplates;
import org.eclipse.wst.xsl.internal.core.tests.TestXSLCore;

import junit.framework.Test;
import junit.framework.TestSuite;


public class XSLCoreTestSuite extends TestSuite {
	public static Test suite() {
		return new XSLCoreTestSuite();
	}

	public XSLCoreTestSuite() {
		super("XSL Core Test Suite");
		addTestSuite(TestIncludedTemplates.class);
		addTestSuite(TestXSLCore.class);
	}
}
