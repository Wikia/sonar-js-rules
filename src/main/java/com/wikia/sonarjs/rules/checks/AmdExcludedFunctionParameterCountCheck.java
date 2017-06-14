package com.wikia.sonarjs.rules.checks;

import com.google.common.collect.ImmutableSet;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.javascript.api.tree.Tree;
import org.sonar.plugins.javascript.api.tree.Tree.Kind;
import org.sonar.plugins.javascript.api.tree.declaration.FunctionDeclarationTree;
import org.sonar.plugins.javascript.api.tree.declaration.MethodDeclarationTree;
import org.sonar.plugins.javascript.api.tree.declaration.ParameterListTree;
import org.sonar.plugins.javascript.api.tree.expression.CallExpressionTree;
import org.sonar.plugins.javascript.api.tree.expression.FunctionExpressionTree;
import org.sonar.plugins.javascript.api.visitors.DoubleDispatchVisitorCheck;

import javax.annotation.Nonnull;
import java.util.Set;

@Rule(key = "AmdExcludedFunctionParameterLimit")
public class AmdExcludedFunctionParameterCountCheck extends DoubleDispatchVisitorCheck {
	final private static String MESSAGE = "Function has %d parameters which is greater than %d authorized.";

	/**
	 * AMD functions where the maximum parameter limit is ignored - it seems this cannot be configured via @RuleProperty
	 */
	final private static Set<String> amdFunctions = ImmutableSet.of("require", "define");

	final private static int DEFAULT_MAXIMUM_ALLOWED_PARAMETER_COUNT = 4;

	@RuleProperty(key = "maximumFunctionParameters")
	private int maximumAllowedParameterCount = DEFAULT_MAXIMUM_ALLOWED_PARAMETER_COUNT;

	private boolean isCurrentFunctionExpressionWhitelisted = false;

	@Override
	public void visitFunctionDeclaration(@Nonnull FunctionDeclarationTree tree) {
		checkParameterCount(tree.parameterClause());
		super.visitFunctionDeclaration(tree);
	}

	@Override
	public void visitMethodDeclaration(@Nonnull MethodDeclarationTree tree) {
		checkParameterCount(tree.parameterClause());
		super.visitMethodDeclaration(tree);
	}

	@Override
	public void visitCallExpression(@Nonnull CallExpressionTree tree) {
		Tree callee = tree.callee();

		if (callee.is(Kind.IDENTIFIER_REFERENCE) && amdFunctions.contains(callee.toString())) {
			isCurrentFunctionExpressionWhitelisted = true;
			super.visitCallExpression(tree);
			isCurrentFunctionExpressionWhitelisted = false;

			return;
		}

		super.visitCallExpression(tree);
	}

	@Override
	public void visitFunctionExpression(@Nonnull FunctionExpressionTree tree) {
		if (!isCurrentFunctionExpressionWhitelisted) {
			checkParameterCount(tree.parameterClause());
		}

		super.visitFunctionExpression(tree);
	}

	private void checkParameterCount(@Nonnull ParameterListTree parameterListTree) {
		Integer paramsCount = parameterListTree.parameters().size();

		if (paramsCount > maximumAllowedParameterCount) {
			addIssue(parameterListTree, String.format(MESSAGE, paramsCount, maximumAllowedParameterCount));
		}
	}

	/**
	 * Used by Sonar to optionally set a user-defined threshold for maximum allowed parameters
	 * @param threshold The maximum allowed parameter count
	 */
	public void setMaximumAllowedParameterCount(int threshold) {
		this.maximumAllowedParameterCount = threshold;
	}
}
