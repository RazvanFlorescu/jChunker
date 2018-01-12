package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import core.ner.NerText;
import core.npc.NounPhraseChunkedText;
import core.text.MergedText;
import core.token.TokenizedText;

import java.io.File;
import java.util.List;

public class TextUtils {

	private TextUtils() {}
	
	public static void marshal(String destination, MergedText mergedText) {
		try {

			File file = new File(destination);
			JAXBContext jaxbContext = JAXBContext.newInstance(NounPhraseChunkedText.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(mergedText, file);
			jaxbMarshaller.marshal(mergedText, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}


	}

	public static TokenizedText unmarshal(String source) {
		try {

			File file = new File(source);
			JAXBContext jaxbContext = JAXBContext.newInstance(TokenizedText.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TokenizedText tokenizedText = (TokenizedText) jaxbUnmarshaller.unmarshal(file);

			return tokenizedText;


		} catch (JAXBException e){
			e.printStackTrace();
			return null;
		}

	}

	public static MergedText merge(TokenizedText tokenizedText, NounPhraseChunkedText chunkedText,
			List<NerText> nerTexts)
	{
		return null;
	}

}
