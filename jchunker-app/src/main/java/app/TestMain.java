package app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Logger;

import core.npc.NounPhraseChunkedText;
import core.npc.NounPhraseChunker;
import core.token.TokenizedText;
import utils.TextUtils;

public class TestMain
{
//	private static final Logger logger = Logger.getLogger(TestMain.class);

	public static void main(String[] args) throws JAXBException, FileNotFoundException
	{
		// init the logger
		BasicConfigurator.configure();
		
		TokenizedText tokenizedText = TextUtils.unmarshal("src\\main\\resources\\regex\\test\\inputText2.xml");
		
		NounPhraseChunkedText chunkedText = NounPhraseChunker.chunk(tokenizedText);
		
		JAXBContext context = JAXBContext.newInstance(NounPhraseChunkedText.class);
		Marshaller m		= context.createMarshaller();
		m.marshal(chunkedText, new FileOutputStream("src\\main\\resources\\regex\\test\\outputText.xml"));
	}

}
