package com.wikia.sonarjs.rules;

import org.junit.Test;
import org.sonar.api.Plugin.Context;
import org.sonar.api.SonarProduct;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.SonarRuntime;
import org.sonar.api.utils.Version;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;

public class PluginTest {
	@Test
	@SuppressWarnings("unchecked")
	public void pluginIsRegistered() {
		JavaScriptRulesPlugin javaScriptRulesPlugin = new JavaScriptRulesPlugin();
		Context context = new Context(getMockSonarRuntime());

		javaScriptRulesPlugin.define(context);

		assertThat((List<Class>) context.getExtensions(), hasItem(JavaScriptRuleDefinitions.class));
	}

	private SonarRuntime getMockSonarRuntime() {
		return new SonarRuntime() {
			@Override
			public Version getApiVersion() {
				return null;
			}

			@Override
			public SonarProduct getProduct() {
				return null;
			}

			@Override
			public SonarQubeSide getSonarQubeSide() {
				return null;
			}
		};
	}
}
