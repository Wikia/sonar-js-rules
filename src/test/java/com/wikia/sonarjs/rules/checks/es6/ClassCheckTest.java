package com.wikia.sonarjs.rules.checks.es6;

import com.wikia.sonarjs.rules.checks.RuleTest;
import org.junit.Test;
import org.sonar.javascript.checks.verifier.JavaScriptCheckVerifier;

import java.io.File;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;

public class ClassCheckTest extends RuleTest {
	@Test
	public void ruleIsLoaded() {
		super.ruleIsLoaded();

		assertThat(actualRuleList, hasItem(ClassCheck.class));
	}

	@Test
	public void classDeclarationIsNoncompliant() {
		JavaScriptCheckVerifier.verify(new ClassCheck(), new File("src/test/resources/ClassCheck.js"));
	}
}
