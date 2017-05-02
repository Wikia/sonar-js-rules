package com.wikia.sonarjs.rules;

import com.wikia.sonarjs.rules.xml.RulesXmlInputStreamFactory;

import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition.Context;

import java.io.IOException;
import java.io.InputStream;

public class RuleDefinitionsTest {

	@Test(expected = IllegalStateException.class)
	public void invalidRulesXmlIsRejected() {
		JavaScriptRuleDefinitions javaScriptRuleDefinitions = new JavaScriptRuleDefinitions(new InvalidRulesXmlInputStreamFactory());
		Context context = new Context();

		javaScriptRuleDefinitions.define(context);
	}

	private static class InvalidRulesXmlInputStreamFactory extends RulesXmlInputStreamFactory {
		@Override
		public InputStream getRulesXmlStream() throws IOException {
			return getClass().getResourceAsStream("/xml/invalidRules.xml");
		}

		@Override
		public InputStream getRulesXsdStream() throws IOException {
			return getClass().getResourceAsStream("/xml/test.xsd");
		}
	}
}
