package com.wikia.sonarjs.rules;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.wikia.sonarjs.rules.xml.RulesXmlReaderFactory;

import org.junit.Test;
import org.sonar.check.Rule;

import java.io.Reader;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class IntegrationTest {
	@Test
	public void rulesXmlIsValid() {
		RulesXmlReaderFactory xmlFactory = new RulesXmlReaderFactory();

		try (Reader xmlReader = xmlFactory.newRulesXmlReader(); Reader xsdReader = xmlFactory.newRulesXsdReader()) {
			StreamSource xsdStreamSource = new StreamSource(xsdReader);
			StreamSource xmlStreamSource = new StreamSource(xmlReader);

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(xsdStreamSource);
			Validator validator = schema.newValidator();

			validator.validate(xmlStreamSource);
		} catch (Exception e) {
			fail("rules.xml does not conform to schema!");
		}
	}

	@Test
	public void checkClassesAreValid() {
		JavaScriptRuleDefinitions javaScriptRuleDefinitions = new JavaScriptRuleDefinitions();
		Class[] checkClasses = javaScriptRuleDefinitions.checkClasses();

		for (Class checkClass: checkClasses) {
			assertTrue(checkClass.isAnnotationPresent(Rule.class));
		}
	}
}
