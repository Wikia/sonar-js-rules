package com.wikia.sonarjs.rules.checks.es6;

import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.expression.ClassTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitorCheck;

import javax.annotation.Nonnull;

@Rule(key = "Classes")
public class ClassCheck extends DoubleDispatchVisitorCheck {
	final private static String MESSAGE = "Replace this ES6 class with a function or object literal.";

	@Override
	public void visitClass(@Nonnull ClassTree tree) {
		addIssue(tree, MESSAGE);

		super.visitClass(tree);
	}
}
