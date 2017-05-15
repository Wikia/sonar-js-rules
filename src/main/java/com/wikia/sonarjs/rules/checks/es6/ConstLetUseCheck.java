package com.wikia.sonarjs.rules.checks.es6;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.Tree;
import org.sonar.plugins.javascript.api.tree.Tree.Kind;
import org.sonar.plugins.javascript.api.visitors.SubscriptionVisitorCheck;

@Rule(key = "ConstLet")
public class ConstLetUseCheck extends SubscriptionVisitorCheck {
	final private static String MESSAGE = "Replace this ES6 variable declaration with var.";

	@Override
	public Set<Kind> nodesToVisit() {
		return ImmutableSet.of(Kind.CONST_DECLARATION, Kind.LET_DECLARATION);
	}

	@Override
	public void visitNode(Tree tree) {
		addIssue(tree, MESSAGE);
	}
}
