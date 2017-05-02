package com.wikia.sonarjs.rules.checks.es6;

import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.expression.YieldExpressionTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitorCheck;

import javax.annotation.Nonnull;

@Rule(key = "Generators")
public class GeneratorCheck extends DoubleDispatchVisitorCheck {
	final private static String MESSAGE = "Refactor this code to not use ES6 generators.";

	@Override
	public void visitYieldExpression(@Nonnull YieldExpressionTree tree) {
		addIssue(tree, MESSAGE);
	}
}
