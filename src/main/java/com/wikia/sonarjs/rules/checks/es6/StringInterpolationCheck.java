package com.wikia.sonarjs.rules.checks.es6;

import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.expression.TemplateLiteralTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitorCheck;

import javax.annotation.Nonnull;

@Rule(key = "StringInterpolation", tags = {"es6"})
public class StringInterpolationCheck extends DoubleDispatchVisitorCheck {
	final private static String MESSAGE = "Replace this ES6 template string with string concatenation";

	@Override
	public void visitTemplateLiteral(@Nonnull TemplateLiteralTree tree) {
		addIssue(tree, MESSAGE);

		super.visitTemplateLiteral(tree);
	}
}
