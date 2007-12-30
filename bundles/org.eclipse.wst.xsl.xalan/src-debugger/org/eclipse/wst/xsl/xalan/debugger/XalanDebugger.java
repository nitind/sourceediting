/*******************************************************************************
 * Copyright (c) 2007 Chase Technology Ltd - http://www.chasetechnology.co.uk
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Doug Satchwell (Chase Technology Ltd) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xsl.xalan.debugger;

import java.util.Map;
import java.util.TooManyListenersException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.apache.xalan.processor.TransformerFactoryImpl;
import org.apache.xalan.trace.TraceManager;
import org.apache.xalan.transformer.TransformerImpl;
import org.eclipse.wst.xsl.debugger.AbstractDebugger;

public class XalanDebugger extends AbstractDebugger
{
	public void setTransformerFactory(TransformerFactory factory)
	{
		TransformerFactoryImpl tfi = (TransformerFactoryImpl) factory;
		tfi.setAttribute(TransformerFactoryImpl.FEATURE_SOURCE_LOCATION, Boolean.TRUE);
		tfi.setAttribute(TransformerFactoryImpl.FEATURE_OPTIMIZE, Boolean.FALSE);
	}

	public void addTransformer(Transformer transformer, Map locations)
	{
		TransformerImpl transformerImpl = (TransformerImpl) transformer;
		TraceManager trMgr = transformerImpl.getTraceManager();
		try
		{
//			XalanPrintTraceListener printer = new XalanPrintTraceListener(new PrintWriter(System.err));
//			printer.m_traceElements = true;
//			printer.m_traceSelection = true;
//			printer.m_traceTemplates = true;
//			trMgr.addTraceListener(printer);
			
			XalanTraceListener traceListener = new XalanTraceListener(transformerImpl.getXPathContext().getVarStack(), this);
			trMgr.addTraceListener(traceListener);
		}
		catch (TooManyListenersException e)
		{
			// ignore
		}
	}
}
