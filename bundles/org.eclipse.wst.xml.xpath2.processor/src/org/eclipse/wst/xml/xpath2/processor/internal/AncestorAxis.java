/*******************************************************************************
 * Copyright (c) 2005, 2010 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;

/**
 * Returns the ancestors of the context node, this always includes the root
 * node.
 */
public class AncestorAxis extends ParentAxis {

	/**
	 * Get the ancestors of the context node.
	 * 
	 * @param node
	 *            is the type of node.
	 */
	// XXX unify this with descendants axis ?
	public void iterate(NodeType node, ResultBuffer copyInto) {

		int before = copyInto.size();
		// get the parent
		super.iterate(node, copyInto);

		// no parent
		if (copyInto.size() == before)
			return;

		NodeType parent = (NodeType) copyInto.item(before);

		// get ancestors of parent
		iterate(parent, copyInto);
	}

}
