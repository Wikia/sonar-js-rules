package com.wikia.sonarjs.rules.checks;

import org.junit.Test;
import org.sonar.javascript.checks.verifier.JavaScriptCheckVerifier;

import java.io.File;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class AmdExcludedFunctionParameterCountCheckTest extends RuleTest {
	@Test
	public void ruleIsLoaded() {
		super.ruleIsLoaded();

		assertThat(actualRuleList, hasItem(AmdExcludedFunctionParameterCountCheck.class));
	}

	@Test
	public void nonWhiteListedFunctionsWithTooManyParametersAreNonCompliant() {
		JavaScriptCheckVerifier.verify(new AmdExcludedFunctionParameterCountCheck(), new File("src/test/resources/AmdExcludedFunctionParameterCountCheck.js"));
	}
}
