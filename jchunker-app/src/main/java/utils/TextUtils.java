package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import core.NerText;
import core.text.MergedText;
import core.text.NounPharseChunkedText;
import core.text.TokenizedText;

import java.io.File;
import java.util.List;

public class TextUtils {

	public static void marshal(String destination, MergedText mergedText) {
		try {

			File file = new File(destination);
			JAXBContext jaxbContext = JAXBContext.newInstance(NounPharseChunkedText.class);
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

			File file = new File("src\\main\\resources\\inputFiles\\inputText.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(TokenizedText.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			TokenizedText tokenizedText = (TokenizedText) jaxbUnmarshaller.unmarshal(file);

			return tokenizedText;


		} catch (JAXBException e){
			e.printStackTrace();
			return null;
		}

	}

	public static MergedText merge(List<NerText> chunkedTexts, NounPharseChunkedText chunkedText)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public static MergedText merge(TokenizedText tokenizedText, NounPharseChunkedText chunkedText,
			List<NerText> nerTexts)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
