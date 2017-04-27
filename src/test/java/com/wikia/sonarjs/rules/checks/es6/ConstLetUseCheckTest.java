package com.wikia.sonarjs.rules.checks.es6;

import com.wikia.sonarjs.rules.checks.RuleTest;
import org.junit.Test;
import org.sonar.javascript.checks.verifier.JavaScriptCheckVerifier;

import java.io.File;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class ConstLetUseCheckTest extends RuleTest {
	@Test
	public void ruleIsLoaded() {
		super.ruleIsLoaded();

		assertThat(actualRuleList, hasItem(ConstLetUseCheck.class));
	}

	@Test
	public void constAndLetAreNoncompliant() {
		JavaScriptCheckVerifier.verify(new ConstLetUseCheck(), new File("src/test/resources/ConstLetUseCheck.js"));
	}
}
