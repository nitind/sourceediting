/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.css.core.modelhandler;


import org.eclipse.wst.css.core.internal.document.CSSModelImpl;
import org.eclipse.wst.css.core.internal.encoding.CSSDocumentLoader;
import org.eclipse.wst.sse.core.AbstractModelLoader;
import org.eclipse.wst.sse.core.IStructuredModel;
import org.eclipse.wst.sse.core.IModelLoader;
import org.eclipse.wst.sse.core.document.IDocumentLoader;


/**
 * 
 */
public class CSSModelLoader extends AbstractModelLoader {
	/**
	 * CSSLoader constructor comment.
	 */
	public CSSModelLoader() {
		super();
	}

	/*
	 * @see IModelLoader#newModel()
	 */
	public IStructuredModel newModel() {
		IStructuredModel model = new CSSModelImpl();
		// now done in create
		// model.setStructuredDocument(createNewStructuredDocument());
		// model.setFactoryRegistry(defaultFactoryRegistry());
		return model;
	}

	public IModelLoader newInstance() {
		return new CSSModelLoader();
	}

	public IDocumentLoader getDocumentLoader() {
		if (documentLoaderInstance == null) {
			documentLoaderInstance = new CSSDocumentLoader();
		}
		return documentLoaderInstance;
	}
}