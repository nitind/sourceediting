/*******************************************************************************
 * Copyright (c) 2009 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     David Carver - STAR - initial api and implementation bug 262765 
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.testsuite.functions;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.*;
import org.eclipse.wst.xml.xpath2.processor.ast.XPath;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class SeqUnorderedFuncTest extends AbstractPsychoPathTest {

   //Arg: sequence of string.
   public void test_fn_unordered_mix_args_001() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-001.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-001.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string.
   public void test_fn_unordered_mix_args_002() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-002.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-002.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string.
   public void test_fn_unordered_mix_args_003() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-003.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-003.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string.
   public void test_fn_unordered_mix_args_004() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-004.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-004.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and anyURI.
   public void test_fn_unordered_mix_args_005() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-005.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-005.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string.
   public void test_fn_unordered_mix_args_006() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-006.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-006.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and integer.
   public void test_fn_unordered_mix_args_007() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-007.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-007.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string , decimal and integer.
   public void test_fn_unordered_mix_args_008() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-008.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-008.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and float.
   public void test_fn_unordered_mix_args_009() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-009.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-009.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and float.
   public void test_fn_unordered_mix_args_010() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-010.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-010.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and float.
   public void test_fn_unordered_mix_args_011() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-011.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-011.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and float.
   public void test_fn_unordered_mix_args_012() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-012.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-012.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and double.
   public void test_fn_unordered_mix_args_013() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-013.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-013.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and double.
   public void test_fn_unordered_mix_args_014() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-014.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-014.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and double.
   public void test_fn_unordered_mix_args_015() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-015.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-015.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and double.
   public void test_fn_unordered_mix_args_016() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-016.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-016.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and boolean.
   public void test_fn_unordered_mix_args_017() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-017.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-017.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and boolean.
   public void test_fn_unordered_mix_args_018() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-018.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-018.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and boolean.
   public void test_fn_unordered_mix_args_019() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-019.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-019.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and boolean.
   public void test_fn_unordered_mix_args_020() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-020.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-020.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and date.
   public void test_fn_unordered_mix_args_021() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-021.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-021.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and dateTime.
   public void test_fn_unordered_mix_args_022() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-022.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-022.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: sequence of string and time.
   public void test_fn_unordered_mix_args_023() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-023.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/GeneralSeqFunc/SeqUnorderedFunc/fn-unordered-mix-args-023.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      DynamicContext dc = setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  XPath path = compileXPath(dc, xpath);
	
	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
	      ResultSequence rs = eval.evaluate(path);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

}
      