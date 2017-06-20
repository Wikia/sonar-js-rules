package com.wikia.sonarjs.rules.checks;

import org.junit.Test;
import org.sonar.javascript.checks.verifier.JavaScriptCheckVerifier;

import java.io.File;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class JqueryProxyUsageCheckTest extends RuleTest {
	@Test
	public void ruleIsLoaded() {
		super.ruleIsLoaded();

		assertThat(actualRuleList, hasItem(JqueryProxyUsageCheck.class));
	}

	@Test
	public void jQueryProxyUsageIsNoncompliant() {
		JavaScriptCheckVerifier.verify(new JqueryProxyUsageCheck(), new File("src/test/resources/JqueryProxyUsageCheck.js"));
	}
}
