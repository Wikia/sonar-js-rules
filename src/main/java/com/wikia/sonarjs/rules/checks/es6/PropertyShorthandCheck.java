package com.wikia.sonarjs.rules.checks.es6;

import org.sonar.check.Rule;
import org.sonar.plugins.javascript.api.tree.Tree.Kind;
import org.sonar.plugins.javascript.api.tree.expression.ObjectLiteralTree;
import org.sonar.plugins.javascript.api.tree.expression.PairPropertyTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitorCheck;

import javax.annotation.Nonnull;

@Rule(key = "PropertyShorthands")
public class PropertyShorthandCheck extends DoubleDispatchVisitorCheck {
	final private static String MESSAGE = "Replace this ES6 shorthand property declaration with a pair property.";

	@Override
	public void visitObjectLiteral(@Nonnull ObjectLiteralTree tree) {
		tree.properties().forEach(entry -> {
			if (entry instanceof PairPropertyTree) {
				PairPropertyTree pairPropertyTree = (PairPropertyTree) entry;
				if (!(pairPropertyTree.key().is(Kind.STRING_LITERAL, Kind.IDENTIFIER_REFERENCE))) {
					addIssue(pairPropertyTree, MESSAGE);
				}
			} else {
				addIssue(entry, MESSAGE);
			}
		});

		super.visitObjectLiteral(tree);
	}
}
