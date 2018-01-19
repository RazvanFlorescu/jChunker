package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import core.ner.NameEntity;
import core.ner.NerText;
import core.npc.NounPhrase;
import core.npc.NounPhraseChunkedText;
import core.text.MergedText;
import core.token.Token;
import core.token.TokenizedText;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TextUtils {

	private TextUtils() {}
	
	public static void marshal(String destination, MergedText mergedText) {
		try {

			File file = new File(destination);
			JAXBContext jaxbContext = JAXBContext.newInstance(MergedText.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(mergedText, file);
//			jaxbMarshaller.marshal(mergedText, System.out);

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
		MergedText mergedText = new MergedText();
		Set<Token> tokenSet = new LinkedHashSet<>();
//		for(Token token : tokenizedText.getTokens()){
//			NounPhrase nP = GeneralUtils.getNpcByTokenId(chunkedText,token.getId());
//			List<NameEntity> ners = GeneralUtils.getNersByTokenId(nerTexts,token.getId());
//			mergedText.getOutputList().add(token);
//			if(nP != null){
//				mergedText.getOutputList().add(nP);
//			}
//			if(ners!=null){
//				for(NameEntity ner : ners){
//					mergedText.getOutputList().add(ner);
//				}
//			}
//		}
		
		for(NounPhrase np : chunkedText.getNounPhraseList())
		{
			// add the noun phrase
			mergedText.getOutputList().add(np);
			
			// find the main token
			tokenSet.add(GeneralUtils.getTokenFromId(tokenizedText.getTokens(), np.getTokenId()));
			
			// find the tokens from the token list
			String[] tokenIds = np.getIdList().split(",");
			for(String tokenId : tokenIds)
			{
				if("".equals(tokenId))
					continue;
				
				tokenSet.add(GeneralUtils.getTokenFromId(tokenizedText.getTokens(), Integer.parseInt(tokenId)));
			}
		}
		
		for(NerText nerText : nerTexts)
		{
			for(NameEntity ne : nerText.getNameEntities())
			{
				// add the name entity
				mergedText.getOutputList().add(ne);
				
				// find the token
				tokenSet.add(GeneralUtils.getTokenFromId(tokenizedText.getTokens(), ne.getId()));
			}
		}
		
		// insert the tokens
		for(Token token : tokenSet)
			mergedText.getOutputList().add(token);
		
		return mergedText;
	}

}
