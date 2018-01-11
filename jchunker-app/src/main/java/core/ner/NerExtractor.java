package core.ner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.token.Token;
import core.token.TokenizedText;

public class NerExtractor
{
	private Recognizer nerPeopleName;
	private Recognizer nerOrganizationEntity;
	private Recognizer nerTimeEntity;
	
	public NerExtractor() throws IOException 
	{
		nerTimeEntity = new NerTimeEntity("src\\main\\resources\\gazeteer\\time.txt");
		nerOrganizationEntity = new NerOrganizationEntity("src\\main\\resources\\gazeteer\\organization.txt"); ;
		nerPeopleName = new NerPeopleNameEntity("src\\main\\resources\\gazeteer\\personNames");;
		
	}
	
	/**
	 * Takes a tokenized text and produces a list of NerTexts
	 * 
	 * @param tokenizedText
	 * @return List<NerText>
	 */
	public List<NerText> extract(TokenizedText tokenizedText)
	{
	  List<NerText>result = new ArrayList<>();	
	  
	  NerText peopleName = new NerText();
	  NerText organization = new NerText();
	  NerText time = new NerText();
	  
		  for (Token token : tokenizedText.getTokens()) {
			if(this.nerOrganizationEntity.recognize(token)!=null){
				organization.getNameEntities().add(this.nerOrganizationEntity.recognize(token));
			}
			if(this.nerPeopleName.recognize(token)!=null){
				peopleName.getNameEntities().add(this.nerPeopleName.recognize(token));
			}
			if(this.nerTimeEntity.recognize(token)!=null){
			    time.getNameEntities().add(this.nerTimeEntity.recognize(token));
			}
		}
		result.add(peopleName);
		result.add(organization);
		result.add(time);
        
		return result;
	}

}
