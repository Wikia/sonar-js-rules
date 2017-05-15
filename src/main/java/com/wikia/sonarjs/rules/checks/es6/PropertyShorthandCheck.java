package com.wikia.sonarjs.rules.checks.es6;

import javax.annotation.Nonnull;
import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.Tree.Kind;
import org.sonar.plugins.javascript.api.tree.expression.ObjectLiteralTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitorCheck;

@Rule(key = "PropertyShorthands")
public class PropertyShorthandCheck extends DoubleDispatchVisitorCheck {
	final private static String MESSAGE = "Replace this ES6 shorthand property declaration with a pair property.";

	@Override
	public void visitObjectLiteral(@Nonnull ObjectLiteralTree tree) {
		tree.properties().stream()
				.filter(property -> !property.is(Kind.PAIR_PROPERTY))
				.forEach(shortHandProperty -> addIssue(shortHandProperty, MESSAGE));

		super.visitObjectLiteral(tree);
	}
}
