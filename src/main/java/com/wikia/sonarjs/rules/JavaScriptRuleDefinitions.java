package com.wikia.sonarjs.rules;

import com.wikia.sonarjs.rules.checks.*;
import com.wikia.sonarjs.rules.checks.es6.*;
import com.wikia.sonarjs.rules.xml.RulesXmlReaderFactory;

import com.google.common.annotations.VisibleForTesting;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.plugins.javascript.api.CustomJavaScriptRulesDefinition;

import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class JavaScriptRuleDefinitions extends CustomJavaScriptRulesDefinition {
	private RulesXmlReaderFactory xmlReaderFactory;

	public JavaScriptRuleDefinitions() {
		xmlReaderFactory = new RulesXmlReaderFactory();
	}

	@VisibleForTesting
	JavaScriptRuleDefinitions(RulesXmlReaderFactory factory) {
		xmlReaderFactory = factory;
	}

	@Override
	public String repositoryKey() {
		return "wikia-js-rules";
	}

	@Override
	public String repositoryName() {
		return "Wikia JS Rules";
	}

	@Override
	public void define(Context context) {
		try (
			Reader xmlStreamValidationReader = xmlReaderFactory.newRulesXmlReader();
			Reader xmlStreamRulesDefinitionReader = xmlReaderFactory.newRulesXmlReader();
			Reader xsdStreamReader = xmlReaderFactory.newRulesXsdReader()
		) {
			StreamSource xsdStreamSource = new StreamSource(xsdStreamReader);
			StreamSource xmlStreamSource = new StreamSource(xmlStreamValidationReader);

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(xsdStreamSource);
			Validator validator = schema.newValidator();

			validator.validate(xmlStreamSource);

			NewRepository repo = context.createRepository(repositoryKey(), "js").setName(repositoryName());
			RulesDefinitionXmlLoader xmlLoader = new RulesDefinitionXmlLoader();

			xmlLoader.load(repo, xmlStreamRulesDefinitionReader);
			repo.done();
		} catch (Exception e) {
			throw new IllegalStateException("rules.xml not found or invalid", e);
		}
	}

	@Override
	public Class[] checkClasses() {
		return new Class[] {
			AmdExcludedFunctionParameterCountCheck.class,
			ArrowFunctionCheck.class,
			ClassCheck.class,
			ConstLetUseCheck.class,
			GeneratorCheck.class,
			IteratorCheck.class,
			JqueryProxyUsageCheck.class,
			LiteralsCheck.class,
			PropertyShorthandCheck.class,
			StringInterpolationCheck.class
		};
	}
}
