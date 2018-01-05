package utils;

import core.ChunkedText;
import core.TokenizedText;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class TextUtils {

	public static void marshal(String destination, ChunkedText chunkedText) {
		try {

			File file = new File(destination);
			JAXBContext jaxbContext = JAXBContext.newInstance(ChunkedText.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(chunkedText, file);
			jaxbMarshaller.marshal(chunkedText, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}


	}

	public static TokenizedText unmarshal() {
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

}
