/*******************************************************************************
 * Copyright (c) 2009 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     David Carver (STAR) - bug 262046 - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xsl.launching.tests.testcase;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.internal.ui.launchConfigurations.LaunchShortcutExtension;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.wst.xsl.launching.XSLLaunchConfigurationConstants;
import org.eclipse.wst.xsl.launching.tests.AbstractLaunchingTest;
import org.eclipse.wst.xsl.launching.tests.MockXSLLaunchShortCut;

/**
 * Tests the capabilities of launch shortcuts from the <code>LaunchShortcuts</code> extension point
 * 
 * @since 1.0
 */
public class LaunchShortcutTests extends AbstractLaunchingTest {

	
	/**
	 * Constructor
	 * @param name
	 */
	public LaunchShortcutTests(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		IPath path = folder.getFullPath();
		addLaunchConfiguration(path, "SimpleTransform.launch");
	}
	
	/**
	 * Tests that the short cut is defined via the extension point.
	 */
	public void testShortcutExtensionPointConfigured() {
		LaunchShortcutExtension ext = getLaunchShortcutExtension(XSL_LAUNCH_SHORTCUT_ID);
		assertNotNull("XSLT stylesheet shortcut not found", ext); //$NON-NLS-1$
	}
	
	public void testXSLLaunchConfigTypeDefined() {
		MockXSLLaunchShortCut shortCut = new MockXSLLaunchShortCut();

		String typeid = XSLLaunchConfigurationConstants.ID_LAUNCH_CONFIG_TYPE; 
		assertEquals("Unexpected shortcut Type", typeid, shortCut.testGetConfigurationType().getIdentifier());
	}

	/**
	 * Tests that the local java app shortcut does not support some fake type id 'foo'
	 */
	public void testAssociatedConfigurationTypeNotSupported() {
		LaunchShortcutExtension ext = getLaunchShortcutExtension(XSL_LAUNCH_SHORTCUT_ID);
		assertNotNull("XSLT shortcut not found", ext); //$NON-NLS-1$
		String typeid = "org.eclipse.jdt.launching.foo"; //$NON-NLS-1$
		assertTrue("local xslt app shortcut should not support foo", !ext.getAssociatedConfigurationTypes().contains(typeid)); //$NON-NLS-1$
	}	
	
	
	/**
	 * Returns a listing of all applicable <code>LaunchShortcutExtension</code>s for the given
	 * launch configuration type id.
	 * @param typeid the id of the launch configuration
	 * @return a listing of <code>LaunchShortcutExtension</code>s that are associated with the specified launch configuration
	 * type id or an empty list, never <code>null</code>
	 * 
	 * @since 1.0
	 */
	public List getApplicableLaunchShortcuts(String typeid) {
		List list = new ArrayList();
		LaunchShortcutExtension ext = null;
		List shortcuts = getLaunchConfigurationManager().getLaunchShortcuts();
		for(int i = 0; i < shortcuts.size(); i++) {
			ext = (LaunchShortcutExtension) shortcuts.get(i);
			if(ext.getAssociatedConfigurationTypes().contains(typeid) && !WorkbenchActivityHelper.filterItem(ext)) {
				list.add(ext);
			}
		}
		return list;
	}	
}
