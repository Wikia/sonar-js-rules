package com.wikia.sonarjs.rules.checks.es6;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.Tree;
import org.sonar.plugins.javascript.api.tree.Tree.Kind;
import org.sonar.plugins.javascript.api.tree.expression.LiteralTree;
import org.sonar.plugins.javascript.api.visitors.SubscriptionVisitorCheck;

@Rule(key = "Literals")
public class LiteralsCheck extends SubscriptionVisitorCheck {
	final private static String MESSAGE = "Replace this ES6 numeric literal with a decimal or hexadecimal number.";

	@Override
	public Set<Kind> nodesToVisit() {
		return ImmutableSet.of(Kind.NUMERIC_LITERAL);
	}

	@Override
	public void visitNode(Tree tree) {
		LiteralTree literalTree = (LiteralTree) tree;
		String literal = literalTree.value();

		if (literal.startsWith("0b") || literal.startsWith("0o")) {
			addIssue(tree, MESSAGE);
		}
	}
}
