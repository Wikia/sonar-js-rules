package com.wikia.sonarjs.rules.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.Tree.Kind;
import org.sonar.plugins.javascript.api.tree.expression.ExpressionTree;
import org.sonar.plugins.javascript.api.tree.expression.MemberExpressionTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitorCheck;

import javax.annotation.Nonnull;

@Rule(key = "JqueryProxyUsage")
public class JqueryProxyUsageCheck extends DoubleDispatchVisitorCheck {
	final private static String MESSAGE = "Convert this use of $.proxy polyfill to Function.prototype.bind.";

	final private static String $ = "$";
	final private static String PROXY = "proxy";

	@Override
	public void visitMemberExpression(@Nonnull MemberExpressionTree tree) {
		ExpressionTree object = tree.object();
		ExpressionTree property = tree.property();

		if (object.is(Kind.IDENTIFIER_REFERENCE) && property.is(Kind.PROPERTY_IDENTIFIER)) {
			checkForJqueryProxy(object, property);
		}

		super.visitMemberExpression(tree);
	}

	private void checkForJqueryProxy(ExpressionTree object, ExpressionTree property) {
		if (object.toString().equals($) && property.toString().equals(PROXY)) {
			addIssue(property, MESSAGE);
		}
	}
}
