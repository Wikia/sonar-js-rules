package com.wikia.sonarjs.rules.checks;

import com.wikia.sonarjs.rules.JavaScriptRuleDefinitions;

import java.util.Arrays;
import java.util.List;

public abstract class RuleTest {
	protected List<Class> actualRuleList;

	public void ruleIsLoaded() {
		JavaScriptRuleDefinitions javaScriptRuleDefinitions = new JavaScriptRuleDefinitions();
		Class[] ruleClasses = javaScriptRuleDefinitions.checkClasses();

		actualRuleList = Arrays.asList(ruleClasses);
	}
}
