package com.wikia.sonarjs.rules.checks.es6;

import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.expression.ArrowFunctionTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitorCheck;

import javax.annotation.Nonnull;

@Rule(key = "ArrowFunctions", tags = {"es6"})
public class ArrowFunctionCheck extends DoubleDispatchVisitorCheck {
	final private static String MESSAGE = "Convert this ES6 arrow function into a regular one.";

	@Override
	public void visitArrowFunction(@Nonnull ArrowFunctionTree tree) {
		addIssue(tree, MESSAGE);

		super.visitArrowFunction(tree);
	}
}
