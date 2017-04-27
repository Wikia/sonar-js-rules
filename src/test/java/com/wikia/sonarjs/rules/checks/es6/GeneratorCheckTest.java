package com.wikia.sonarjs.rules.checks.es6;

import com.wikia.sonarjs.rules.checks.RuleTest;
import org.junit.Test;
import org.sonar.javascript.checks.verifier.JavaScriptCheckVerifier;

import java.io.File;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class GeneratorCheckTest extends RuleTest {
	@Test
	public void ruleIsLoaded() {
		super.ruleIsLoaded();

		assertThat(actualRuleList, hasItem(GeneratorCheck.class));
	}

	@Test
	public void generatorsAreNoncompliant() {
		JavaScriptCheckVerifier.verify(new GeneratorCheck(), new File("src/test/resources/GeneratorCheck.js"));
	}
}
