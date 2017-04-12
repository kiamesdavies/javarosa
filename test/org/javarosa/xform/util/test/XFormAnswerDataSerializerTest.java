/*
 * Copyright (C) 2009 JavaRosa
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.javarosa.xform.util.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Date;

import org.javarosa.core.model.data.DateData;
import org.javarosa.core.model.data.IntegerData;
import org.javarosa.core.model.data.SelectOneData;
import org.javarosa.core.model.data.StringData;
import org.javarosa.core.model.data.TimeData;
import org.javarosa.core.model.instance.TreeElement;
import org.javarosa.xform.util.XFormAnswerDataSerializer;

/**
 * Note that this is just a start and doesn't cover direct comparisons
 * for most values.
 * 
 * @author Clayton Sims
 *
 */
public class XFormAnswerDataSerializerTest extends TestCase {
	final String stringDataValue = "String Data Value";
	final Integer integerDataValue = new Integer(5);
	final Date dateDataValue = new Date();
	final Date timeDataValue = new Date();
	
	StringData stringData;
	IntegerData integerData;
	DateData dateData;
	SelectOneData selectData;
	TimeData timeData;
	
	TreeElement stringElement = new TreeElement();
	TreeElement intElement = new TreeElement();
	TreeElement dateElement = new TreeElement();
	TreeElement selectElement = new TreeElement();
	TreeElement timeElement = new TreeElement();
	
	XFormAnswerDataSerializer serializer;
	
	private static int NUM_TESTS = 5;
	
	public void setUp() throws Exception {
		super.setUp();
		stringData = new StringData(stringDataValue);
		stringElement.setValue(stringData);
		
		integerData = new IntegerData(integerDataValue);
		intElement.setValue(integerData);
		
		dateData = new DateData(dateDataValue); 
		dateElement.setValue(dateData);
		
		timeData = new TimeData(timeDataValue); 
		timeElement.setValue(timeData);
		
		serializer = new XFormAnswerDataSerializer();
	}

	public XFormAnswerDataSerializerTest(String name) {
		super(name);
		System.out.println("Running " + this.getClass().getName() + " test: " + name + "...");
	}

	public static Test suite() {
		TestSuite aSuite = new TestSuite();

		for (int i = 1; i <= NUM_TESTS; i++) {
			final int testID = i;

			aSuite.addTest(new XFormAnswerDataSerializerTest(testMaster(testID)));
		}

		return aSuite;
	}
	public static String testMaster (int testID) {
		switch (testID) {
			case 1: return "testString";
			case 2: return "testInteger";
			case 3: return "testDate";
			case 4: return "testTime";
			case 5: return "testSelect";
		}
		throw new IllegalStateException("Unexpected index");
	}
	
	public void testString() {
		assertTrue("Serializer Incorrectly Reports Inability to Serializer String", serializer.canSerialize(stringElement.getValue()));
		Object answerData = serializer.serializeAnswerData(stringData);
		assertNotNull("Serializer returns Null for valid String Data", answerData);
		assertEquals("Serializer returns incorrect string serialization", answerData, stringDataValue);
	}
	
	public void testInteger() {
		assertTrue("Serializer Incorrectly Reports Inability to Serializer Integer", serializer.canSerialize(intElement.getValue()));
		Object answerData = serializer.serializeAnswerData(integerData);
		assertNotNull("Serializer returns Null for valid Integer Data", answerData);
		//assertEquals("Serializer returns incorrect Integer serialization", answerData, integerDataValue);
	}
	
	public void testDate() {
		assertTrue("Serializer Incorrectly Reports Inability to Serializer Date", serializer.canSerialize(dateElement.getValue()));
		Object answerData = serializer.serializeAnswerData(dateData);
		assertNotNull("Serializer returns Null for valid Date Data", answerData);
	}
	
	public void testTime() {
		assertTrue("Serializer Incorrectly Reports Inability to Serializer Time", serializer.canSerialize(timeElement.getValue()));
		Object answerData = serializer.serializeAnswerData(timeData);
		assertNotNull("Serializer returns Null for valid Time Data", answerData);
	}
	
	public void testSelect() {
		//No select tests yet.
	}
}
