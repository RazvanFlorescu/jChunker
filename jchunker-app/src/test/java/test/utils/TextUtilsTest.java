package test.utils;
import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import core.ner.NameEntity;
import core.ner.NerText;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.npc.NounPhraseChunkedText;
import core.text.MergedText;
import core.token.Token;
import core.token.TokenizedText;
import test.artefacts.TextUtilsArtefacts;
import utils.TextUtils;


public class TextUtilsTest {

	private MergedText mergedText;
	private TextUtils textUtils;
	private TokenizedText tokenizedText;

	@Test
	public void testMarshal() throws JAXBException {
		//when
		MergedText mergedText= TextUtilsArtefacts.createMergedText();
		String destination = "C:\\Users\\raluca.plugariu\\Documents\\STS\\jChunker\\jchunker-app\\src\\main\\resources\\inputFiles\\outputTextTest.xml";
		File file = new File(destination);
		//then
		textUtils.marshal(destination,mergedText);
		//assertTrue(file.exists());
		//assertTrue(file.length()>1);
	}

	@Test
	public void testUnMarshal(){
		//when
		TokenizedText testText = TextUtilsArtefacts.createTokenizedText();
		System.out.print("====");
		//then
		TokenizedText tokText = TextUtils.unmarshal("C:\\Users\\raluca.plugariu\\Documents\\STS\\jChunker\\jchunker-app\\src\\main\\resources\\regex\\test\\inputText2.xml");
		//System.out.print("===="+tokText.getTokens().get(0).getId());
		//assertThat(tokText.getTokens().get(1).getId()).isEqualTo(testText.getTokens().get(1).getId());
		//assertThat(tokText.getTokens().get(1).getLemma()).isEqualTo(testText.getTokens().get(1).getLemma());

	}

	@Test
	public void testMerge(){
		//when
		TokenizedText testText = TextUtilsArtefacts.createTokenizedText();
		MergedText mergedTextTest= TextUtilsArtefacts.createMergedText();
		NounPhraseChunkedText npc = TextUtilsArtefacts.createNounPhraseChunkedText();
		List<NerText> nerList = TextUtilsArtefacts.createnerTextList();


		//then
		MergedText mergedText = textUtils.merge(testText,npc,nerList);
		assertTrue(mergedText.getOutputList().size()>0);

	}





}