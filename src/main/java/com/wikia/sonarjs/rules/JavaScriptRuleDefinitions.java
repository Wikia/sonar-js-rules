package com.wikia.sonarjs.rules;

import com.wikia.sonarjs.rules.checks.es6.*;
import org.sonar.plugins.javascript.api.CustomJavaScriptRulesDefinition;

public class JavaScriptRuleDefinitions extends CustomJavaScriptRulesDefinition {
	@Override
	public String repositoryKey() {
		return "wikia-js-rules";
	}

	@Override
	public String repositoryName() {
		return "Wikia JS Rules";
	}

	@Override
	public Class[] checkClasses() {
		return new Class[] {
			ArrowFunctionCheck.class,
			ClassCheck.class,
			ConstLetUseCheck.class,
			GeneratorCheck.class,
			IteratorCheck.class,
			LiteralsCheck.class,
			PropertyShorthandCheck.class,
			StringInterpolationCheck.class
		};
	}
}
