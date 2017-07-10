package com.wikia.sonarjs.rules;

import com.wikia.sonarjs.rules.xml.RulesXmlReaderFactory;

import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class RuleDefinitionsTest {

	@Test(expected = IllegalStateException.class)
	public void invalidRulesXmlIsRejected() {
		JavaScriptRuleDefinitions javaScriptRuleDefinitions = new JavaScriptRuleDefinitions(new InvalidRulesXmlReaderFactory());
		Context context = new Context();

		javaScriptRuleDefinitions.define(context);
	}

	private static class InvalidRulesXmlReaderFactory extends RulesXmlReaderFactory {
		@Override
		public Reader newRulesXmlReader() throws IOException {
			InputStream invalidRulesStream = getClass().getResourceAsStream("/xml/invalidRules.xml");

			return new InputStreamReader(invalidRulesStream);
		}

		@Override
		public Reader newRulesXsdReader() throws IOException {
			InputStream invalidXsdStream = getClass().getResourceAsStream("/xml/test.xsd");

			return new InputStreamReader(invalidXsdStream);
		}
	}
}
