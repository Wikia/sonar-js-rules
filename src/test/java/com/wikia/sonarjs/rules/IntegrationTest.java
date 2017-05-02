package com.wikia.sonarjs.rules;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.wikia.sonarjs.rules.xml.RulesXmlInputStreamFactory;

import org.junit.Test;
import org.sonar.check.Rule;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class IntegrationTest {
	@Test
	public void rulesXmlIsValid() {
		RulesXmlInputStreamFactory xmlFactory = new RulesXmlInputStreamFactory();

		try (InputStream xmlStream = xmlFactory.getRulesXmlStream(); InputStream xsdStream = xmlFactory.getRulesXsdStream()) {
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new StreamSource(xsdStream));
			Validator validator = schema.newValidator();

			validator.validate(new StreamSource(xmlStream));
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
