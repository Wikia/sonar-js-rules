package com.wikia.sonarjs.rules.checks.es6;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.Tree;
import org.sonar.plugins.javascript.api.tree.Tree.Kind;
import org.sonar.plugins.javascript.api.visitors.SubscriptionVisitorCheck;

@Rule(key = "Iterators")
public class IteratorCheck extends SubscriptionVisitorCheck {
	final private static String MESSAGE = "Replace this for-of loop with a for-in or for/while loop or iteration function.";

	@Override
	public Set<Kind> nodesToVisit() {
		return ImmutableSet.of(Kind.FOR_OF_STATEMENT);
	}

	@Override
	public void visitNode(Tree tree) {
		addIssue(tree, MESSAGE);
	}
}
