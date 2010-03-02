/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.jsdt.web.core.tests.translation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import junit.framework.TestCase;

import org.eclipse.wst.jsdt.web.core.javascript.IJsTranslation;
import org.eclipse.wst.jsdt.web.core.javascript.JsTranslationAdapter;
import org.eclipse.wst.jsdt.web.core.javascript.JsTranslationAdapterFactory;
import org.eclipse.wst.jsdt.web.core.tests.Activator;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;



public class TestHtmlTranslation extends TestCase {

	
	protected IModelManager fModelManager = null;
	private static final String testFilesDirectory = "testFiles";
	public TestHtmlTranslation(){
		fModelManager = StructuredModelManager.getModelManager();
	}
	
	public void testHTMLFormat() {
		// get model
		IStructuredModel structuredModel = getModel("test1.html");
		assertNotNull("missing test model", structuredModel);
		
		// compare
		String formatted = structuredModel.getStructuredDocument().get();
		String expectedFileName = "test1.html";
		String expected = getFile(expectedFileName);
		
		JsTranslationAdapterFactory.setupAdapterFactory(structuredModel);
		JsTranslationAdapter translationAdapter = (JsTranslationAdapter) ((IDOMModel) structuredModel).getDocument().getAdapterFor(IJsTranslation.class);
		IJsTranslation translation = translationAdapter.getJsTranslation(false);
		assertTrue("expected function definition is missing", translation.getJsText().indexOf("function blah()") >= 0);

		// release model
		structuredModel.releaseFromRead();
	}

	protected String readFile(String fileName) {		
		String inputString = null;
		InputStream fileInputStream = null;

		try {
			URL url = Activator.getDefault().getBundle().getEntry(fileName);
			fileInputStream = url.openStream();

			byte[] inputBuffer = new byte[1024];
			inputString = new String();
			int bytesRead = -1;

			while (true) {
				bytesRead = fileInputStream.read(inputBuffer);
				if (bytesRead == -1)
					break;
				String bufferString = new String(inputBuffer);
				bufferString = bufferString.substring(0, bytesRead);
				inputString = inputString.concat(bufferString);
			}

			if (fileInputStream != null)
				fileInputStream.close();
		}
		catch (IOException exception) {
			StringWriter s = new StringWriter();
			exception.printStackTrace(new PrintWriter(s));
			fail(s.toString());
		}

		return inputString;
	}

	protected static void printException(Exception exception) {
		exception.printStackTrace();
	}

	protected IStructuredModel getModel(String fileName) {
		IStructuredModel structuredModel = null;
		InputStream inputStream = null;

		try {
			String input = getFile(fileName);
			inputStream = new ByteArrayInputStream(input.getBytes("UTF8"));
			String id = inputStream.toString().concat(fileName);
			
			structuredModel = fModelManager.getModelForRead(id, inputStream, null);
		}
		catch (Exception exception) {
			StringWriter s = new StringWriter();
			exception.printStackTrace(new PrintWriter(s));
			fail(s.toString());
		}
		finally {
			try {
				inputStream.close();
			}
			catch (IOException exception) {
				// should already be closed
			}
		}

		return structuredModel;
	}

	protected IStructuredModel getSharedModel(String id, String contents) {
		IStructuredModel structuredModel = null;
		InputStream inputStream = null;

		try {
			inputStream = new ByteArrayInputStream(contents.getBytes("UTF8"));
			
			structuredModel = fModelManager.getModelForRead(id, inputStream, null);
		}
		catch (Exception exception) {
			StringWriter s = new StringWriter();
			exception.printStackTrace(new PrintWriter(s));
			fail(s.toString());
		}
		finally {
			try {
				inputStream.close();
			}
			catch (IOException exception) {
				// should already be closed
			}
		}

		return structuredModel;
	}

	protected String getFile(String fileName) {
		return readFile("/testFiles/".concat(fileName));
	}
	
	public void testMangleTagInJS() {
		// get model
		String fileName = getName() + ".html";
		IStructuredModel structuredModel = getSharedModel(fileName, "<script> var a = <custom:tag/>5; if(a < 4) {} ; </script>");
		assertNotNull("missing test model", structuredModel);
		
		// do translation
		JsTranslationAdapterFactory.setupAdapterFactory(structuredModel);
		JsTranslationAdapter translationAdapter = (JsTranslationAdapter) ((IDOMModel) structuredModel).getDocument().getAdapterFor(IJsTranslation.class);
		IJsTranslation translation = translationAdapter.getJsTranslation(false);
		String translated = translation.getJsText();
		assertTrue("tag included", translated.indexOf("<custom") < 0);
		assertTrue("tag included", translated.indexOf("/>") < 0);

		assertTrue("problems found in translation ", translation.getProblems().isEmpty());

		// release model
		structuredModel.releaseFromRead();
	}
	public void testMangleServerSideAndClientTagInJS() {
		// get model
		String fileName = getName() + ".html";
		IStructuredModel structuredModel = getSharedModel(fileName, "<script> var a = <custom:tag/>5;\nif(a < <%= 4 %>) {} ; </script>");
		assertNotNull("missing test model", structuredModel);
		
		// do translation
		JsTranslationAdapterFactory.setupAdapterFactory(structuredModel);
		JsTranslationAdapter translationAdapter = (JsTranslationAdapter) ((IDOMModel) structuredModel).getDocument().getAdapterFor(IJsTranslation.class);
		IJsTranslation translation = translationAdapter.getJsTranslation(false);
		String translated = translation.getJsText();
		assertTrue("server-side script block included\n" + translated, translated.indexOf("<%") < 0);
		assertTrue("server-side script block included\n" + translated, translated.indexOf("%>") < 0);
		assertTrue("tag included\n" + translated, translated.indexOf("custom") < 0);
		assertTrue("tag included\n" + translated, translated.indexOf("/>") < 0);
		assertTrue("var dropped\n" + translated, translated.indexOf("var a = ") > -1);
		assertTrue("if dropped\n" + translated, translated.indexOf("5;\nif(a <") > -1);
		assertTrue("block dropped\n" + translated, translated.indexOf(") {} ; ") > -1); 

		assertTrue("problems found in translation ", translation.getProblems().isEmpty());

		// release model
		structuredModel.releaseFromRead();
	}
	public void testMangleTagAndServerSideInJS() {
		// get model
		String fileName = getName() + ".html";
		IStructuredModel structuredModel = getSharedModel(fileName, "<script> var a = <%= 4 %>5;\nif(a < <custom:tag/>) {} ; </script>");
		assertNotNull("missing test model", structuredModel);
		
		// do translation
		JsTranslationAdapterFactory.setupAdapterFactory(structuredModel);
		JsTranslationAdapter translationAdapter = (JsTranslationAdapter) ((IDOMModel) structuredModel).getDocument().getAdapterFor(IJsTranslation.class);
		IJsTranslation translation = translationAdapter.getJsTranslation(false);
		String translated = translation.getJsText();
		assertTrue("server-side script block included\n" + translated, translated.indexOf("<%") < 0);
		assertTrue("server-side script block included\n" + translated, translated.indexOf("%>") < 0);
		assertTrue("tag included\n" + translated, translated.indexOf("custom") < 0);
		assertTrue("tag included\n" + translated, translated.indexOf("/>") < 0);
		assertTrue("var dropped\n" + translated, translated.indexOf("var a = ") > -1);
		assertTrue("if dropped\n" + translated, translated.indexOf("5;\nif(a < ") > -1);
		assertTrue("block dropped\n" + translated, translated.indexOf(") {} ; ") > -1); 

		assertTrue("problems found in translation ", translation.getProblems().isEmpty());

		// release model
		structuredModel.releaseFromRead();
	}
//	public void testMangleOverlappingTagAndServerSideInJS() {		
//		// get model
//		String fileName = getName() + ".html";
//		IStructuredModel structuredModel = getSharedModel(fileName, "<script> var a = <%= 4 %>5;\nif(a < <custom:tag attr=\"<%=%>\"/>) {} ; </script>");
//		assertNotNull("missing test model", structuredModel);
//		
//		// do translation
//		JsTranslationAdapterFactory.setupAdapterFactory(structuredModel);
//		JsTranslationAdapter translationAdapter = (JsTranslationAdapter) ((IDOMModel) structuredModel).getDocument().getAdapterFor(IJsTranslation.class);
//		IJsTranslation translation = translationAdapter.getJsTranslation(false);
//		String translated = translation.getJsText();
//		assertTrue("server-side script block included\n" + translated, translated.indexOf("<%") < 0);
//		assertTrue("server-side script block included\n" + translated, translated.indexOf("%>") < 0);
//		assertTrue("tag included\n" + translated, translated.indexOf("custom") < 0);
//		assertTrue("tag included\n" + translated, translated.indexOf("/>") < 0);
//		assertTrue("var dropped\n" + translated, translated.indexOf("var a = ") > -1);
//		assertTrue("if dropped\n" + translated, translated.indexOf("5;\nif(a < ") > -1);
//		assertTrue("block dropped\n" + translated, translated.indexOf(") {} ; ") > -1); 
//
//		assertTrue("problems found in translation ", translation.getProblems().isEmpty());
//
//		// release model
//		structuredModel.releaseFromRead();
//	}
	public void testMangleServerSideInJSCheckProblems() {
		// get model
		String fileName = getName() + ".html";
		IStructuredModel structuredModel = getSharedModel(fileName, "<script> var text = <%= javaObject.getText() %>; </script>");
		assertNotNull("missing test model", structuredModel);
		
		// do translation
		JsTranslationAdapterFactory.setupAdapterFactory(structuredModel);
		JsTranslationAdapter translationAdapter = (JsTranslationAdapter) ((IDOMModel) structuredModel).getDocument().getAdapterFor(IJsTranslation.class);
		IJsTranslation translation = translationAdapter.getJsTranslation(false);
		String translated = translation.getJsText();
		assertTrue("server-side script block included", translated.indexOf("<%") < 0);
		assertTrue("server-side script block included", translated.indexOf("%>") < 0);
		assertTrue("var dropped", translated.indexOf("var text = ") > -1);
		assertTrue("problems found in translation ", translation.getProblems().isEmpty());

		// release model
		structuredModel.releaseFromRead();
	}
	public void testJustClientTagInJS() {
		// get model
		String fileName = getName() + ".html";
		IStructuredModel structuredModel = getSharedModel(fileName, "<script><custom:tag /></script>");
		assertNotNull("missing test model", structuredModel);
		
		// do translation
		JsTranslationAdapterFactory.setupAdapterFactory(structuredModel);
		JsTranslationAdapter translationAdapter = (JsTranslationAdapter) ((IDOMModel) structuredModel).getDocument().getAdapterFor(IJsTranslation.class);
		IJsTranslation translation = translationAdapter.getJsTranslation(false);
		String translated = translation.getJsText();
		assertTrue("server-side script block included\n" + translated, translated.indexOf("<") < 0);
		assertTrue("server-side script block included\n" + translated, translated.indexOf("/") < 0);
		assertTrue("server-side script block included\n" + translated, translated.indexOf(">") < 0);
		assertTrue("content not included\n" + translated, translated.length() != 0); 

		// release model
		structuredModel.releaseFromRead();
	}
	public void testJustServerSideInJS() {
		// get model
		String fileName = getName() + ".html";
		IStructuredModel structuredModel = getSharedModel(fileName, "<script><%= %></script>");
		assertNotNull("missing test model", structuredModel);
		
		// do translation
		JsTranslationAdapterFactory.setupAdapterFactory(structuredModel);
		JsTranslationAdapter translationAdapter = (JsTranslationAdapter) ((IDOMModel) structuredModel).getDocument().getAdapterFor(IJsTranslation.class);
		IJsTranslation translation = translationAdapter.getJsTranslation(false);
		String translated = translation.getJsText();
		assertTrue("server-side script block included\n" + translated, translated.indexOf("<%") < 0);
		assertTrue("server-side script block included\n" + translated, translated.indexOf("%>") < 0);
		assertTrue("content not included\n" + translated, translated.length() != 0); 

		// release model
		structuredModel.releaseFromRead();
	}
}
