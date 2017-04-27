package com.wikia.sonarjs.rules;

import org.sonar.api.Plugin;

import javax.annotation.Nonnull;

public class JavaScriptRulesPlugin implements Plugin {
	@Override
	public void define(@Nonnull Context context) {
		context.addExtension(JavaScriptRuleDefinitions.class);
	}
}
