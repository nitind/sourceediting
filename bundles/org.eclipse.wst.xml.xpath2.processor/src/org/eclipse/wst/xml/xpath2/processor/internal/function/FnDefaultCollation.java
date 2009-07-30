/*******************************************************************************
 * Copyright (c) 2009 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     David Carver (STAR) - initial API and implementation
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

/**
 * <p>
 * Summary: Returns the value of the default collation property from the static
 * context. Components of the static context are discussed in Section C.1 Static
 * Context Components.
 * </p>
 * 
 * <p>
 * Note:
 * </p>
 * 
 * <p>
 * The default collation property can never be undefined. If it is not
 * explicitly defined, a system defined default can be invoked. If this is not
 * provided, the Unicode code point collation
 * (http://www.w3.org/2005/xpath-functions/collation/codepoint) is used.
 * </p>
 * 
 * @author dcarver
 * @since 1.1
 */
public class FnDefaultCollation extends Function {

	private static final String DEFAULT_COLLATION = "http://www.w3.org/2005/xpath-functions/collation/codepoint";

	public FnDefaultCollation() {
		super(new QName("default-collation"), 0);
	}

	@Override
	public ResultSequence evaluate(Collection args) throws DynamicError {
		assert args.size() >= min_arity() && args.size() <= max_arity();
		ResultSequence rs = ResultSequenceFactory.create_new();
		rs.add(new XSString(DEFAULT_COLLATION));
		return rs;
	}

}
