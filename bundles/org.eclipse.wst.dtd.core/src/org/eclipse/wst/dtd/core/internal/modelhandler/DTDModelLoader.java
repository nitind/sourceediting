/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Jens Lukowski/Innoopract - initial renaming/restructuring
 *     
 *******************************************************************************/
package org.eclipse.wst.dtd.core.internal.modelhandler;

import org.eclipse.wst.dtd.core.internal.document.DTDModelImpl;
import org.eclipse.wst.dtd.core.internal.encoding.DTDDocumentLoader;
import org.eclipse.wst.sse.core.AbstractModelLoader;
import org.eclipse.wst.sse.core.IStructuredModel;
import org.eclipse.wst.sse.core.IModelLoader;
import org.eclipse.wst.sse.core.document.IDocumentLoader;



public final class DTDModelLoader extends AbstractModelLoader {
	public DTDModelLoader() {
		super();
	}

	public IDocumentLoader getDocumentLoader() {
		if (documentLoaderInstance == null) {
			documentLoaderInstance = new DTDDocumentLoader();
		}
		return documentLoaderInstance;
	}

	public IModelLoader newInstance() {
		return new DTDModelLoader();
	}

	public IStructuredModel newModel() {
		IStructuredModel model = new DTDModelImpl();
		// now done in create
		// model.setStructuredDocument(createNewStructuredDocument());
		// model.setFactoryRegistry(defaultFactoryRegistry());
		return model;
	}
}
