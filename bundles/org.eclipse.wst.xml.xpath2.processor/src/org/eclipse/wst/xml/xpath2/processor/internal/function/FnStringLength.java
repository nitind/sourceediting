/*******************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0
 *     Mukul Gandhi - bug 274471 - improvements to string-length function (support for arity 0)
 *     Mukul Gandhi - bug 274805 - improvements to xs:integer data type  
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import org.eclipse.wst.xml.xpath2.processor.DynamicContext;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.*;
import org.eclipse.wst.xml.xpath2.processor.internal.types.*;

import java.math.BigInteger;
import java.util.*;

/**
 * <p>
 * Function to calculate string length.
 * </p>
 * 
 * <p>
 * Usage: fn:string-length($arg as xs:string?) as xs:integer
 * </p>
 * 
 * <p>
 * This class returns an xs:integer equal to the length in characters of the
 * value of $arg.
 * </p>
 * 
 * <p>
 * If the value of $arg is the empty sequence, the xs:integer 0 is returned.
 * </p>
 */
public class FnStringLength extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnStringLength
	 */
	public FnStringLength() {
		super(new QName("string-length"), 0, 1);
	}

	/**
	 * Evaluate the arguments.
	 * 
	 * @param args
	 *            are evaluated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The evaluation of the string length of the arguments.
	 */
	@Override
	public ResultSequence evaluate(Collection args) throws DynamicError {
		return string_length(args, dynamic_context());
	}

	/**
	 * Obtain the string length of the arguments.
	 * 
	 * @param args
	 *            are used to obtain the string length.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The result of obtaining the string length from the arguments.
	 */
	public static ResultSequence string_length(Collection args, DynamicContext d_context)
			throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		ResultSequence arg1 = null;

		if (cargs.isEmpty()) {
		  // support for arity = 0
		  return getResultSetForArityZero(d_context);
		}
		else {
		  arg1 = (ResultSequence) cargs.iterator().next();
		}
		
		if (arg1.empty()) {
		  // support for arity = 0
		  return getResultSetForArityZero(d_context);
		}

		String str = ((XSString) arg1.first()).value();

		ResultSequence rs = ResultSequenceFactory.create_new();
		rs.add(new XSInteger(BigInteger.valueOf(str.length())));

		return rs;
	}

	/**
	 * Calculate the expected arguments.
	 * 
	 * @return The expected arguments.
	 */
	public static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(new XSString(), SeqType.OCC_QMARK));
		}

		return _expected_args;
	}
	
	/*
	 * Helper function for arity 0
	 */
	private static ResultSequence getResultSetForArityZero(DynamicContext d_context) {
		ResultSequence rs = ResultSequenceFactory.create_new();
		
		AnyType contextItem = d_context.context_item();
		if (contextItem != null) {
		  // if context item is defined, then that is the default argument
		  // to fn:string function
		  rs.add(new XSInteger(BigInteger.valueOf(contextItem.string_value().length())));
		}
		else {
		  rs.add(new XSInteger(BigInteger.valueOf(0)));
		}
		return rs;
	}
}
