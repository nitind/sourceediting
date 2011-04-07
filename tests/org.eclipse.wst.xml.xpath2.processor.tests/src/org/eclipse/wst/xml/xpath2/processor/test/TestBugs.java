/*******************************************************************************
 * Copyright (c) 2009, 2010 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     David Carver (STAR) - initial API and implementation
 *     Mukul Gandhi - bug 273719 - improvements to fn:string-length function
 *     Mukul Gandhi - bug 273795 - improvements to fn:substring function
 *     Mukul Gandhi - bug 274471 - improvements to fn:string function
 *     Mukul Gandhi - bug 274725 - improvements to fn:base-uri function
 *     Mukul Gandhi - bug 274731 - improvements to fn:document-uri function
 *     Mukul Gandhi - bug 274784 - improvements to xs:boolean data type
 *     Mukul Gandhi - bug 274805 - improvements to xs:integer data type
 *     Mukul Gandhi - bug 274952 - implements xs:long data type
 *     Mukul Gandhi - bug 277599 - implements xs:nonPositiveInteger data type
 *     Mukul Gandhi - bug 277602 - implements xs:negativeInteger data type
 *     Mukul Gandhi - bug 277599 - implements xs:nonPositiveInteger data type
 *     Mukul Gandhi - bug 277608   implements xs:short data type
 *                    bug 277609   implements xs:nonNegativeInteger data type
 *                    bug 277629   implements xs:unsignedLong data type
 *                    bug 277632   implements xs:positiveInteger data type
 *                    bug 277639   implements xs:byte data type
 *                    bug 277642   implements xs:unsignedInt data type
 *                    bug 277645   implements xs:unsignedShort data type
 *                    bug 277650   implements xs:unsignedByte data type
 *                    bug 279373   improvements to multiply operation on xs:yearMonthDuration
 *                                 data type.
 *                    bug 279376   improvements to xs:yearMonthDuration division operation
 *                    bug 281046   implementation of xs:base64Binary data type                                
 *  Jesper S Moller - bug 286061   correct handling of quoted string 
 *  Jesper S Moller - bug 280555 - Add pluggable collation support
 *  Jesper S Moller - bug 297958   Fix fn:nilled for elements
 *  Mukul Gandhi    - bug 298519   improvements to fn:number implementation, catering
 *                                 to node arguments.
 *  Mukul Gandhi    - bug 301539   fix for "context undefined" bug in case of zero
 *                                 arity of fn:name function.
 *  Mukul Gandhi    - bug 309585   implementation of xs:normalizedString data type                             
 * Jesper S Moller  - bug 311480 - fix problem with name matching on keywords 
 * Jesper S Moller  - bug 312191 - instance of test fails with partial matches
 *  Mukul Gandhi    - bug 318313 - improvements to computation of typed values of nodes,
 *                                 when validated by XML Schema primitive types
 *  Mukul Gandhi    - bug 323900 - improving computing the typed value of element &
 *                                 attribute nodes, where the schema type of nodes
 *                                 are simple, with varieties 'list' and 'union'.
 *  Mukul Gandhi    - bug 325262 - providing ability to store an XPath2 sequence into
 *                                 an user-defined variable.
 *  Mukul Gandhi    - bug 334478   implementation of xs:token data type
 *  Mukul Gandhi    - bug 334842 - improving support for the data types Name, NCName, ENTITY, 
 *                                 ID, IDREF and NMTOKEN.
 *  Mukul Gandhi    - bug 338494 - prohibiting xpath expressions starting with / or // to be parsed.                                
 *  Mukul Gandhi    - bug 280798 - PsychoPath support for JDK 1.4
 *  Mukul Gandhi    - bug 338494 - prohibiting xpath expressions starting with / or // to be parsed.
 *  Mukul Gandhi    - bug 338999 - improving compliance of function 'fn:subsequence'. implementing full arity support.
 *  Mukul Gandhi    - bug 339025 - fixes to fn:distinct-values function. ability to find distinct values on node items.
 *  Mukul Gandhi    - bug 341862 - improvements to computation of typed value of xs:boolean nodes.                                 
 *  Jesper Steen Moller  - bug 340933 - Migrate tests to new XPath2 API
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import java.math.BigInteger;
import java.net.URL;
import java.util.Comparator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xerces.xs.XSModel;
import org.eclipse.core.runtime.Platform;
import org.eclipse.wst.xml.xpath2.api.CollationProvider;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDecimal;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDouble;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDuration;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSFloat;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInteger;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.osgi.framework.Bundle;
import org.xml.sax.InputSource;

public class TestBugs extends AbstractPsychoPathTest {

	private static final String URN_X_ECLIPSE_XPATH20_FUNKY_COLLATOR = "urn:x-eclipse:xpath20:funky-collator";

	private Bundle bundle;

	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		bundle = Platform
				.getBundle("org.eclipse.wst.xml.xpath2.processor.tests");

	}


	public void testNamesWhichAreKeywords() throws Exception {
		// Bug 273719
		URL fileURL = bundle.getEntry("/bugTestFiles/bug311480.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

//		String xpath = "($input-context/atomic:root/atomic:integer) union ($input-context/atomic:root/atomic:integer)";
		String xpath = "(/element/eq eq 'eq') or //child::xs:*";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testStringLengthWithElementArg() throws Exception {
		// Bug 273719
		URL fileURL = bundle.getEntry("/bugTestFiles/bug273719.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "string-length(x) > 2";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testBug273795Arity2() throws Exception {
		// Bug 273795
		URL fileURL = bundle.getEntry("/bugTestFiles/bug273795.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// test with arity 2
		String xpath = "substring(x, 3) = 'happy'";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testBug273795Arity3() throws Exception {
		// Bug 273795
		URL fileURL = bundle.getEntry("/bugTestFiles/bug273795.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// test with arity 3
		String xpath = "substring(x, 3, 4) = 'happ'";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testStringFunctionBug274471() throws Exception {
		// Bug 274471
		URL fileURL = bundle.getEntry("/bugTestFiles/bug274471.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "x/string() = 'unhappy'";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testStringLengthFunctionBug274471() throws Exception {
		// Bug 274471. string-length() with arity 0
		URL fileURL = bundle.getEntry("/bugTestFiles/bug274471.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "x/string-length() = 7";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("false", actual);
	}

	public void testNormalizeSpaceFunctionBug274471() throws Exception {
		// Bug 274471. normalize-space() with arity 0
		URL fileURL = bundle.getEntry("/bugTestFiles/bug274471.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "x/normalize-space() = 'unhappy'";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testAnyUriEqualityBug() throws Exception {
		// Bug 274719
		// reusing the XML document from another bug
		URL fileURL = bundle.getEntry("/bugTestFiles/bug274471.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:anyURI('abc') eq xs:anyURI('abc')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testBaseUriBug() throws Exception {
		// Bug 274725

		// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		loadDOMDocument(new URL("http://resolved-locally/xml/note.xml"));

		// for testing this bug, we read the XML document from the web.
		// this ensures, that base-uri property of DOM is not null.
		// domDoc = docBuilder.parse("http://resolved-locally/xml/note.xml");

		// we pass XSModel as null for this test case. Otherwise, we would
		// get an exception.
		setupDynamicContext(null);

		String xpath = "base-uri(note) eq xs:anyURI('http://resolved-locally/xml/note.xml')";

		// please note: The below XPath would also work, with base-uri using
		// arity 0.
		// String xpath =
		// "note/base-uri() eq xs:anyURI('http://resolved-locally/xml/note.xml')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testDocumentUriBug() throws Exception {
		// Bug 274731
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		
		InputSource inputSource = getTestSource("http://resolved-locally/xml/note.xml");
		domDoc = docBuilder.parse(inputSource);

		setupDynamicContext(null);

		String xpath = "document-uri(/) eq xs:anyURI('http://resolved-locally/xml/note.xml')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = "false";

		if (result != null) {
			actual = result.string_value();
		}

		assertEquals("true", actual);
	}

	public void testBooleanTypeBug() throws Exception {
		// Bug 274784
		// reusing the XML document from another bug
		URL fileURL = bundle.getEntry("/bugTestFiles/bug273719.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:boolean('1') eq xs:boolean('true')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testDateConstructorBug() throws Exception {
		// Bug 274792
		URL fileURL = bundle.getEntry("/bugTestFiles/bug274792.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:date(x) eq xs:date('2009-01-01')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testIntegerDataTypeBug() throws Exception {
		// Bug 274805
		URL fileURL = bundle.getEntry("/bugTestFiles/bug274805.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:integer(x) gt 100";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testLongDataType() throws Exception {
		// Bug 274952
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// long min value is -9223372036854775808
		// and max value can be 9223372036854775807
		String xpath = "xs:long('9223372036854775807') gt 0";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testIntDataType() throws Exception {
		// Bug 275105
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// int min value is -2147483648
		// and max value can be 2147483647
		String xpath = "xs:int('2147483647') gt 0";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testSchemaAwarenessForAttributes() throws Exception {
		// Bug 276134
		URL fileURL = bundle.getEntry("/bugTestFiles/bug276134.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug276134.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "person/@dob eq xs:date('2006-12-10')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);

	}

	public void testSchemaAwarenessForElements() throws Exception {
		// Bug 276134
		URL fileURL = bundle.getEntry("/bugTestFiles/bug276134_2.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug276134_2.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "person/dob eq xs:date('2006-12-10')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testNilled() throws Exception {
		// This is a terrible shortcoming in the test suite, I'd say
		URL fileURL = bundle.getEntry("/bugTestFiles/bugNilled.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bugNilled.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		assertTrue(evaluateBoolean("empty( nilled( / ) )"));
		assertTrue(evaluateBoolean("empty( nilled( /root/@attr1 ) )"));
		assertTrue(evaluateBoolean("empty( nilled( /root/element1/text() ) )"));

		assertFalse(evaluateBoolean("nilled(/root/element1)"));
		assertTrue(evaluateBoolean("nilled(/root/element2)"));
		assertFalse(evaluateBoolean("nilled(/root/element3)"));
		assertFalse(evaluateBoolean("nilled(/root/element4)"));
	}

	// I can't stand to see so much duplicated code!!!
	private boolean evaluateBoolean(String xpath) throws Exception {
		compileXPath(xpath);
		ResultSequence rs = evaluate(domDoc);
		XSBoolean result = (XSBoolean) rs.first();

		return result.value();
	}

	public void testXSNonPositiveInteger() throws Exception {
		// Bug 277599
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");

		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// min value of xs:nonPositiveInteger is -INF
		// max value is 0
		String xpath = "xs:nonPositiveInteger('0') eq 0";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSNegativeInteger() throws Exception {
		// Bug 277602
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");

		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// min value of xs:negativeInteger is -INF
		// max value is -1
		String xpath = "xs:negativeInteger('-1') eq -1";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSShort() throws Exception {
		// Bug 277608
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");

		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// min value of xs:short is -32768
		// max value of xs:short is 32767
		String xpath = "xs:short('-32768') eq -32768";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSNonNegativeInteger() throws Exception {
		// Bug 277609
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");

		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// min value of xs:nonNegativeInteger is 0
		// max value of xs:nonNegativeInteger is INF
		String xpath = "xs:nonNegativeInteger('0') eq 0";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSUnsignedLong() throws Exception {
		// Bug 277629
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");

		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// min value of xs:unsignedLong is 0
		// max value of xs:unsignedLong is 18446744073709551615
		String xpath = "xs:unsignedLong('0') eq 0";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSPositiveInteger() throws Exception {
		// Bug 277632
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");

		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// min value of xs:positiveInteger is 1
		// max value of xs:positiveInteger is INF
		String xpath = "xs:positiveInteger('1') eq 1";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSByte() throws Exception {
		// Bug 277639
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");

		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// min value of xs:byte is -128
		// max value of xs:byte is 127
		String xpath = "xs:byte('-128') eq -128";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSUnsignedInt() throws Exception {
		// Bug 277642
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");

		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// min value of xs:unsignedInt is 0
		// max value of xs:unsignedInt is 4294967295
		String xpath = "xs:unsignedInt('4294967295') eq xs:unsignedInt('4294967295')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSUnsignedShort() throws Exception {
		// Bug 277645
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// min value of xs:unsignedShort is 0
		// max value of xs:unsignedShort is 65535
		String xpath = "xs:unsignedShort('65535') eq 65535";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSYearMonthDurationMultiply() throws Exception {
		// Bug 279373
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:yearMonthDuration('P2Y11M') * 2.3";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSDuration result = (XSDuration) rs.first();

		String actual = result.string_value();

		assertEquals("P6Y9M", actual);
	}

	public void testXSYearMonthDurationDivide1() throws Exception {
		// Bug 279376
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:yearMonthDuration('P2Y11M') div 1.5";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSDuration result = (XSDuration) rs.first();

		String actual = result.string_value();

		assertEquals("P1Y11M", actual);
	}

	public void testXSYearMonthDurationDivide2() throws Exception {
		// Bug 279376
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:yearMonthDuration('P3Y4M') div xs:yearMonthDuration('-P1Y4M')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSDecimal result = (XSDecimal) rs.first();

		String actual = result.string_value();

		assertEquals("-2.5", actual);
	}

	public void testXSDayTimeDurationMultiply() throws Exception {
		// Bug 279377
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:dayTimeDuration('PT2H10M') * 2.1";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSDuration result = (XSDuration) rs.first();

		String actual = result.string_value();

		assertEquals("PT4H33M", actual);
	}

	public void testXSDayTimeDurationDivide() throws Exception {
		// Bug 279377
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:dayTimeDuration('P1DT2H30M10.5S') div 1.5";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSDuration result = (XSDuration) rs.first();

		String actual = result.string_value();

		assertEquals("PT17H40M7S", actual);
	}

	public void testNegativeZeroDouble() throws Exception {
		// Bug 279406
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "-(xs:double('0'))";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSDouble result = (XSDouble) rs.first();

		String actual = result.string_value();

		assertEquals("-0", actual);
	}

	public void testNegativeZeroFloat() throws Exception {
		// Bug 279406
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "-(xs:float('0'))";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSFloat result = (XSFloat) rs.first();

		String actual = result.string_value();

		assertEquals("-0", actual);
	}

	public void testXSUnsignedByte() throws Exception {
		// Bug 277650
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// min value of xs:unsignedByte is 0
		// max value of xs:unsignedByte is 255
		String xpath = "xs:unsignedByte('255') eq 255";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSBase64Binary() throws Exception {
		// Bug 281046
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:base64Binary('cmxjZ3R4c3JidnllcmVuZG91aWpsbXV5Z2NhamxpcmJkaWFhbmFob2VsYXVwZmJ1Z2dmanl2eHlzYmhheXFtZXR0anV2dG1q') eq xs:base64Binary('cmxjZ3R4c3JidnllcmVuZG91aWpsbXV5Z2NhamxpcmJkaWFhbmFob2VsYXVwZmJ1Z2dmanl2eHlzYmhheXFtZXR0anV2dG1q')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXSHexBinary() throws Exception {
		// Bug 281054
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:hexBinary('767479716c6a647663') eq xs:hexBinary('767479716c6a647663')";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testElementTypedValue() throws Exception {
		// test for fix in ElementType.java, involving incorrectly computing
		// typed value of element node, in case of validating element node,
		// with a user defined simple XSD type.
		URL fileURL = bundle.getEntry("/bugTestFiles/elementTypedValueBug.xml");
		URL schemaURL = bundle
				.getEntry("/bugTestFiles/elementTypedValueBug.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/Transportation/mode eq 'air'";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);

	}

	public void testBug286061_quoted_string_literals_no_normalize()
			throws Exception {

		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "'\"\"'"; // the expression '""' contains no escapes
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		String resultValue = rs.first().string_value();

		assertEquals("\"\"", resultValue);
	}

	public void testBug286061_quoted_string_literals() throws Exception {

		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "concat(  'Don''t try this' ,  \" at \"\"home\"\",\"  ,  ' she said'  )";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		String resultValue = rs.first().string_value();

		assertEquals("Don't try this at \"home\", she said", resultValue);
	}

	public void testBug280555_collations() throws Exception {
		// Setup context
		setupDynamicContext(null);
		setCollationProvider(createLengthCollatorProvider());

		// Parse expression
		compileXPath(" 'abc' < 'de' ");

		// Evaluate once
		XSBoolean bval = (XSBoolean) evaluate(domDoc).first();
		assertTrue("'abc' < 'de' for normal collations", bval.value());

		// Evaluate again with the funny collator
		setDefaultCollation(URN_X_ECLIPSE_XPATH20_FUNKY_COLLATOR);
		XSBoolean bval2 = (XSBoolean) evaluate(domDoc).first();
		assertFalse("'abc' < 'de' should be false for the strange collations", bval2.value());
	}

	public void testXPathDefaultNamespace() throws Exception {
		// Test for the fix, for xpathDefaultNamespace
		URL fileURL = bundle
				.getEntry("/bugTestFiles/xpathDefNamespaceTest.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		// set up XPath default namespace in Dynamic Context
		setupDynamicContext(schema);
		addXPathDefaultNamespace("http://xyz");

		String xpath = "X/message = 'hello'";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testXPathInstanceOf1() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/elementTypedValueBug.xml");
		URL schemaURL = bundle
				.getEntry("/bugTestFiles/elementTypedValueBug.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/Transportation/mode instance of element()";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testNestedABCs() throws Exception {
		URL fileURL = bundle.getEntry("/bugTestFiles/nested-abc.xml");

		loadDOMDocument(fileURL);
		setupDynamicContext(null);

		String xpath = "a/b[2 > 1]/c[3 < 4]";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

		assertEquals(9, rs.size());
	}

	public void testXPathInstanceOf2() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/elementTypedValueBug.xml");
		URL schemaURL = bundle
				.getEntry("/bugTestFiles/elementTypedValueBug.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/Transportation/mode instance of element(mode)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testXPathInstanceOf3() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/elementTypedValueBug.xml");
		URL schemaURL = bundle
				.getEntry("/bugTestFiles/elementTypedValueBug.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/Transportation/mode instance of element(mode, modeType)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testXPathInstanceOf4() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/elementTypedValueBug.xml");
		URL schemaURL = bundle
				.getEntry("/bugTestFiles/elementTypedValueBug.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/Transportation/mode instance of element(mode, abc)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("false", actual);
	}
	
	public void testXPathInstanceOf5() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/x instance of element(x, x_Type)*";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}

	public void testXPathInstanceOf5_2() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "(/Example/x, /Example) instance of element(x, x_Type)+";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		assertEquals(false, result.value());
	}

	public void testXPathInstanceOf5_3() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "(/Example/x, /Example/x) instance of element(x, x_Type)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		assertFalse(result.value());
	}

	public void testXPathInstanceOf5_4() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "(/Example/x, /Example/x) instance of element(x, x_Type)+";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		assertTrue(result.value());
	}

	public void testXPathInstanceOf5_5() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		assertXPathTrue("/Example/x instance of x_Type+", domDoc);
		assertXPathTrue("/Example/x instance of element(x, x_Type)+", domDoc);
		assertXPathTrue("not (/Example/x instance of element(z, x_Type)+)", domDoc);
		assertXPathTrue("/Example/x[2]/@mesg instance of mesg_Type", domDoc);
		assertXPathTrue("/Example/x[2]/@mesg instance of attribute(mesg, mesg_Type)", domDoc);
		assertXPathTrue("not (/Example/x[2]/@mesg instance of attribute(cesc, mesg_Type))", domDoc);
	}

	public void testXPathInstanceOf6() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/x instance of element(*, x_Type)*";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testXPathInstanceOf7() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/x instance of element(x, x_Type)+";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testXPathNotInstanceOf() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");
	
		loadDOMDocument(fileURL, schemaURL);
	
		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);
	
		setupDynamicContext(schema);
	
		String xpath = "/Example/x[1] instance of element(*, x_Type) and not (/Example/x[1] instance of element(*, y_Type))";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
		XSBoolean result = (XSBoolean) rs.first();
	
		String actual = result.string_value();
	
		assertEquals("true", actual);
	}

	public void testXPathInstanceNonExistantElement() throws Exception {
		// Bug 298267
		URL fileURL = bundle.getEntry("/bugTestFiles/elementTypedValueBug.xml");
		URL schemaURL = bundle
				.getEntry("/bugTestFiles/elementTypedValueBug.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/Transportation/mode instance of element(x)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("false", actual);
	}
	
	public void testFnNumber_Evaluation1() throws Exception {
		// Bug 298519
		URL fileURL = bundle.getEntry("/bugTestFiles/fnNumberBug.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/fnNumberBug.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "number(Example/x) ge 18";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testFnNumber_Evaluation2() throws Exception {
		// Bug 298519
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "number(xs:unsignedByte('20')) ge 18";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testAttrNode_Test1() throws Exception {
		// Bug 298535
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/x[1]/@mesg instance of attribute()";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testAttrNode_Test2() throws Exception {
		// Bug 298535
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/x[1]/@mesg instance of attribute(xx)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("false", actual);
	}
	
	public void testAttrNode_Test3() throws Exception {
		// Bug 298535
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/x[1]/@mesg instance of attribute(*, mesg_Type)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testAttrNode_Test4() throws Exception {
		// Bug 298535
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/x[1]/@mesg instance of attribute(*, abc)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("false", actual);
	}
	
	public void testAttrNode_Test5() throws Exception {
		// Bug 298535
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/x[1]/@mesg instance of attribute(mesg, mesg_Type)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testAttrNode_Test6() throws Exception {
		// Bug 298535
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/x[1]/@mesg instance of attribute(mesg, abc)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("false", actual);
	}
	
	public void testAttrNode_Test7() throws Exception {
		// Bug 298535
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "Example/x/@mesg instance of attribute(mesg, mesg_Type)*";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testFnNameContextUndefined() throws Exception {
		// Bug 301539
		URL fileURL = bundle.getEntry("/bugTestFiles/attrNodeTest.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "Example/*[1]/name() eq 'x'";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testXSNormalizedString() throws Exception {
		// Bug 309585
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "xs:normalizedString('abcs\t') eq xs:normalizedString('abcs')";
		 compileXPath(xpath);

		boolean testSuccess = false;
		try {
		  ResultSequence rs = evaluate(domDoc);
		}
		catch(DynamicError ex) {
		  // a 'DynamicError' exception indicates, that this test is a success 
		  testSuccess = true;
		}
		
		assertTrue(testSuccess);
	}
	
	public void testParseElementKeywordsAsNodes() throws Exception {
		// Bug 311480
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "/element/attribute";
		compileXPath(xpath);
	}
	
	public void testTypedValueEnhancement_primitiveTypes() throws Exception {
		// Bug 318313
		URL fileURL = bundle.getEntry("/bugTestFiles/bug318313.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug318313.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "X gt 99";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testTypedValueEnhancement_Bug323900_1() throws Exception {
		// Bug 323900
		URL fileURL = bundle.getEntry("/bugTestFiles/bug323900_1.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug323900_1.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		String xpath = "data(X) instance of xs:integer+";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testTypedValueEnhancement_Bug323900_2() throws Exception {
		// Bug 323900
		URL fileURL = bundle.getEntry("/bugTestFiles/bug323900_2.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug323900_2.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);

		// 1st test
		String xpath = "data(X) instance of xs:integer+";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("false", actual);
	}
	
	public void testTypedValueEnhancement_Bug323900_3() throws Exception {
		// Bug 323900
		URL fileURL = bundle.getEntry("/bugTestFiles/bug323900_2.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug323900_2.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);
		String xpath = "data(X)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

  		assertTrue(rs.get(0) instanceof XSInteger);
  		assertEquals(BigInteger.ONE, ((XSInteger) rs.get(0)).int_value());

  		assertTrue(rs.get(1) instanceof XSInteger);
  		assertEquals(BigInteger.valueOf(2), ((XSInteger) rs.get(1)).int_value());
          
  		assertTrue(rs.get(2) instanceof XSString);
  		assertEquals("3.3", ((XSString) rs.get(2)).string_value());
	}
	
	public void testTypedValueEnhancement_Bug323900_4() throws Exception {
		// Bug 323900
		URL fileURL = bundle.getEntry("/bugTestFiles/bug323900_3.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug323900_3.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);
		String xpath = "data(X)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

        
		XSString result = (XSString) rs.get(0);
		assertEquals("3.3", result.string_value());
	}
	
	public void testTypedValueEnhancement_Bug323900_5() throws Exception {
		// Bug 323900
		URL fileURL = bundle.getEntry("/bugTestFiles/bug323900_4.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug323900_3.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);
		String xpath = "data(X)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

        
		XSInteger result = (XSInteger) rs.get(0);
		assertEquals("10", result.string_value());
	}
	
	public void testTypedValueEnhancement_BugUsingSeqIntoVariable_1() 
	                                                       throws Exception {
		// Bug 325262
		setupDynamicContext(null);
		
        ResultSequence rs = ResultSequenceFactory.create_new();
        setVariable("value",rs);
        
		String xpath = "deep-equal($value,())";
		compileXPath(xpath);
		ResultSequence rsRes = evaluate(domDoc);
        
		XSBoolean result = (XSBoolean) rsRes.get(0);
		assertEquals("true", result.string_value());
	}
	
	public void testTypedValueEnhancement_BugUsingSeqIntoVariable_2() 
	                                                       throws Exception {
		// Bug 325262
		setupDynamicContext(null);
		
        ResultSequence rs = ResultSequenceFactory.create_new();
        rs.add(new XSInteger(BigInteger.valueOf(2)));
        rs.add(new XSInteger(BigInteger.valueOf(4)));
        rs.add(new XSInteger(BigInteger.valueOf(6)));
        setVariable("value",rs);
        
        // test a
		String xpath = "$value instance of xs:integer+";
		compileXPath(xpath);
		ResultSequence rsRes = evaluate(domDoc);        
		XSBoolean result = (XSBoolean) rsRes.get(0);
		assertEquals("true", result.string_value());
		
		// test b
		xpath = "deep-equal($value, (2, 4, 6))";
		compileXPath(xpath);
		rsRes = evaluate(domDoc);        
		result = (XSBoolean) rsRes.get(0);
		assertEquals("true", result.string_value());
	}
	
	public void testTypedValueEnhancement_BugUsingSeqIntoVariable_3() 
	                                                      throws Exception {
		// Bug 325262
		setupDynamicContext(null);
		
		ResultSequence rs = ResultSequenceFactory.create_new();
        rs.add(new XSInteger(BigInteger.valueOf(2)));
        rs.add(new XSInteger(BigInteger.valueOf(4)));
        rs.add(new XSInteger(BigInteger.valueOf(6)));
        setVariable("value",rs);
        
		String xpath = "count(data($value)) = 3";
		compileXPath(xpath);
		ResultSequence rsRes = evaluate(domDoc);        
		XSBoolean result = (XSBoolean) rsRes.get(0);
		assertEquals("true", result.string_value());
	}
	
	public void testXSToken() throws Exception {
		// Bug 334478
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// the strings in below are not valid tokens (they contain 2 consecutive spaces)
		String xpath = "xs:token('abcs  abcde') eq xs:token('abcs  abcde')";
		compileXPath(xpath);

		boolean testSuccess = false;
		try {
		   ResultSequence rs = evaluate(domDoc);
		}
		catch(DynamicError ex) {
		   // a 'DynamicError' exception indicates, that this test is a success 
		   testSuccess = true;
		}
		
		assertTrue(testSuccess);
	}
	
	public void testBug334842() throws Exception {
		// Bug 334842
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// test a)
		String xpath = "xs:Name('x:abc') eq xs:Name('x:abc')"; 
		compileXPath(xpath);
		ResultSequence rsRes = evaluate(domDoc);
		XSBoolean result = (XSBoolean) rsRes.get(0);
		assertEquals("true", result.string_value());
		
		// test b)
		xpath = "xs:NCName('x:abc') eq xs:NCName('x:abc')"; 
		compileXPath(xpath);
		try {
		   rsRes = evaluate(domDoc);
		   assertTrue(false);
		}
		catch(DynamicError ex) {
		   // a 'DynamicError' exception indicates, that this test is a success 
		   assertTrue(true);
		}
		
		// test c)
		xpath = "xs:NCName('abc') eq xs:NCName('abc')"; 
		compileXPath(xpath);
		rsRes = evaluate(domDoc);
		result = (XSBoolean) rsRes.get(0);
		assertEquals("true", result.string_value());
		
		// test d)
		xpath = "xs:ID('x:abc') eq xs:ID('x:abc')"; 
		compileXPath(xpath);
		try {
		   rsRes = evaluate(domDoc);
		   assertTrue(false);
		}
		catch(DynamicError ex) {
		   // a 'DynamicError' exception indicates, that this test is a success 
		   assertTrue(true);
		}
		
		// test e)
		xpath = "xs:ID('abc') eq xs:ID('abc')"; 
		compileXPath(xpath);
		rsRes = evaluate(domDoc);
		result = (XSBoolean) rsRes.get(0);
		assertEquals("true", result.string_value());
	}
	
	public void testDefNamespaceOnbuiltInTypes() throws Exception {
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		// set up XPath default namespace in Dynamic Context
		setupDynamicContext(schema);
        setVariable("value",new XSString("2.5"));
		addXPathDefaultNamespace("http://www.w3.org/2001/XMLSchema");

		String xpath = "$value castable as double";
		compileXPath(xpath);
		ResultSequence rs = evaluate(domDoc);

		XSBoolean result = (XSBoolean) rs.first();

		String actual = result.string_value();

		assertEquals("true", actual);
	}
	
	public void testExprParsingBeginnigWithRootNode_bug338494() throws Exception {
		// Bug 338494
		bundle = Platform.getBundle("org.w3c.xqts.testsuite");
		URL fileURL = bundle.getEntry("/TestSources/emptydoc.xml");
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		// test a)
		String xpath = "/x";
		try {
		    compileXPath(xpath, true);
		    // test fails
		    assertTrue(false);
		}
		catch(XPathParserException ex) {
			if ("Expression starts with / or //".equals(ex.getMessage())) {
				// test passes
				assertTrue(true);
			}
		}
		
		// test b)
		xpath = "//x";
		try {
		    compileXPath(xpath, true);
		    // test fails
		    assertTrue(false);
		}
		catch(XPathParserException ex) {
			if ("Expression starts with / or //".equals(ex.getMessage())) {
				// test passes
				assertTrue(true);
			}
		}
		
		// test c)
		xpath = "/";
		try {
		    compileXPath(xpath, true);
		    // test fails
		    assertTrue(false);
		}
		catch(XPathParserException ex) {
			if ("Expression starts with / or //".equals(ex.getMessage())) {
				// test passes
				assertTrue(true);
			}
		}
		
		// test d)
		xpath = "x/y[/a]";
		try {
		    compileXPath(xpath, true);
		    // test fails
		    assertTrue(false);
		}
		catch(XPathParserException ex) {
			if ("Expression starts with / or //".equals(ex.getMessage())) {
				// test passes
				assertTrue(true);
			}
		}
		
		// test e)
		xpath = ".//x";
		try {
		    compileXPath(xpath, true);
		    // test passes
		    assertTrue(true);
		}
		catch(XPathParserException ex) {
		   // test fails
		   assertTrue(false);
		}
	}
	
	public void testBug338999_Fnsubsequence() throws Exception {
		// bug 338999
		URL fileURL = bundle.getEntry("/bugTestFiles/bug338999.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug338999.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);

		setupDynamicContext(schema);
		
		// test a)
		String xpath = "count(subsequence(X/*, 2)) eq 2";
		compileXPath(xpath);		
		ResultSequence rs = evaluate(domDoc);
		String actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test b)
		xpath = "subsequence(X/*, 2) instance of element(*, xs:integer)+";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test c)
		xpath = "deep-equal(subsequence((1,2,3,4), 2), (2,3,4))";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test d)
		// hetrogeneous sequence as input. arity 3 mode.
		xpath = "deep-equal(subsequence(('a', 1, 1.5), 2, 2), (1, 1.5))";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test e)
		// hetrogeneous sequence as input. arity 3 mode (startingLoc is < 0).
		xpath = "deep-equal(subsequence(('a', 1, 1.5, 'b'), -2, 3), ())";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
	}
	
	public void testBug339025_distinctValuesOnNodeSequence() throws Exception {
		// bug 339025
		URL fileURL = bundle.getEntry("/bugTestFiles/bug339025.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug339025.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);
		
		setupDynamicContext(schema);;		
		
		// test a)
		String xpath = "count(//a) = count(distinct-values(//a))";
		compileXPath(xpath);		
		ResultSequence rs = evaluate(domDoc);
		String actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test b)
		xpath = "count(X/a) = count(distinct-values(X/a))";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test c)
		xpath = "count(//b) = count(distinct-values(//b))";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("false", actual);
	}
	
	public void testBug341862_TypedBooleanNode() throws Exception {
		// bug 341862
		URL fileURL = bundle.getEntry("/bugTestFiles/bug341862.xml");
		URL schemaURL = bundle.getEntry("/bugTestFiles/bug341862.xsd");

		loadDOMDocument(fileURL, schemaURL);

		// Get XSModel object for the Schema
		XSModel schema = getGrammar(schemaURL);
		
		setupDynamicContext(schema);		

		// test a)
		String xpath = "/X/a[1] = true()";
		compileXPath(xpath);		
		ResultSequence rs = evaluate(domDoc);
		String actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test b)
		xpath = "/X/a[1]/@att = true()";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test c)
		xpath = "/X/a[2] = true()";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test d)
		xpath = "/X/a[2]/@att = true()";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test e)
		xpath = "/X/a[3] = false()";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test f)
		xpath = "/X/a[3]/@att = false()";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test g)
		xpath = "/X/a[4] = false()";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
		
		// test h)
		xpath = "/X/a[4]/@att = false()";
		compileXPath(xpath);		
		rs = evaluate(domDoc);
		actual = ((XSBoolean) rs.first()).string_value();
		assertEquals("true", actual);
	}
	
	private CollationProvider createLengthCollatorProvider() {
		final CollationProvider oldProvider = getStaticContext().getCollationProvider();
		return new CollationProvider() {
			
			public Comparator getCollation(String name) {
				if (name.equals(URN_X_ECLIPSE_XPATH20_FUNKY_COLLATOR)) {
					return new Comparator() {
						public int compare(Object o1, Object o2) {
							return ((String)o1).length() - ((String)o2).length();
						}
					};
				}
				return oldProvider.getCollation(name);
			}

			public String getDefaultCollation() {
				return oldProvider.getDefaultCollation();
			}
		};
	}
	
}
