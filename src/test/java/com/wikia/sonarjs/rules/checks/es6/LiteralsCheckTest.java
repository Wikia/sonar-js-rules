package com.wikia.sonarjs.rules.checks.es6;

import com.wikia.sonarjs.rules.checks.RuleTest;
import org.junit.Test;
import org.sonar.javascript.checks.verifier.JavaScriptCheckVerifier;

import java.io.File;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class LiteralsCheckTest extends RuleTest {
	@Test
	public void ruleIsLoaded() {
		super.ruleIsLoaded();

		assertThat(actualRuleList, hasItem(LiteralsCheck.class));
	}

	@Test
	public void binaryAndOctalNumericLiteralsAreNoncompliant() {
		JavaScriptCheckVerifier.verify(new LiteralsCheck(), new File("src/test/resources/LiteralsCheck.js"));
	}
}
