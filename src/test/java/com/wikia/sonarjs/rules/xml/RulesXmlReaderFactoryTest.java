package com.wikia.sonarjs.rules.xml;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class RulesXmlReaderFactoryTest {
	@Test
	public void inputStreamsAreNotReused() {
		RulesXmlReaderFactory xmlInputStreamFactory = new RulesXmlReaderFactory();

		try (
			Reader firstXmlReader = xmlInputStreamFactory.newRulesXmlReader();
			Reader secondXmlReader = xmlInputStreamFactory.newRulesXmlReader();
			Reader firstXsdReader = xmlInputStreamFactory.newRulesXsdReader();
			Reader secondXsdReader = xmlInputStreamFactory.newRulesXsdReader()
		) {
			assertThat(firstXmlReader, not(sameInstance(secondXmlReader)));
			assertThat(firstXsdReader, not(sameInstance(secondXsdReader)));
		} catch (IOException e) {}
	}
}
