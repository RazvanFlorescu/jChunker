package test.utils;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;

import core.npc.NounPhraseChunkedText;
import core.text.MergedText;
import core.token.Token;
import core.token.TokenizedText;
import utils.TextUtils;


public class TextUtilsTest {
	
	private MergedText mergedText;
	private TextUtils textUtils;
	private TokenizedText tokenizedText;
	@Before
	    public void setupMock() {
		mergedText = mock(MergedText.class);
		textUtils = mock(TextUtils.class);
	}
	
//	@Test
//	public void testMarshal() throws JAXBException {
//		mergedText = new MergedText();
//		File file = new File("src\\main\\resources\\inputFiles\\inputText.xml");
//		JAXBContext jaxbContext = JAXBContext.newInstance(NounPhraseChunkedText.class);
//		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//		TextUtils.marshal("C://", mergedText);
//		verify(jaxbMarshaller).marshal(mergedText, file);
//	}
	
	@Test
	public void testUnMarshal() throws JAXBException {
		//when
		createTokenizedText();
		
		//then
		TokenizedText tokText = textUtils.unmarshal("src\\main\\resources\\inputFiles\\inputText.xml");
		assertThat(tokText.getTokens().get(1).getId()).isEqualTo("2");
		assertThat(tokText.getTokens().get(1).getLemma()).isEqualTo("ieși");
		
	}
	
	private TokenizedText createTokenizedText() {
		List<Token> tokens = new ArrayList<>();
		Token token1 = new Token();
		Token token2 = new Token();
		
		token1.setId("1");
		token1.setLemma("apoi");
		token1.setpartOfSpeech("adverb");
		
		token2.setId("2");
		token2.setLemma("ieși");
		token2.setpartOfSpeech("verb predicative");
		
		tokens.add(token1);
		tokens.add(token2);
		
		TokenizedText tokenizedText = new TokenizedText();
		tokenizedText.setTokens(tokens);
		return tokenizedText;
		
	}
	
	
}