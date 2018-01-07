package core.ner;

import java.io.IOException;
import java.util.List;

import core.token.TokenizedText;

public class NerExtractor
{
	private Recognizer nerPeopleName;
	private Recognizer nerOrganizationEntity;
	private Recognizer nerTimeEntity;
	
	public NerExtractor() throws IOException 
	{
		nerTimeEntity = new NerTimeEntity("C:/path1/path2/gazeteiera.txt");
		// same for the other recognizers
	}
	
	/**
	 * Takes a tokenized text and produces a list of NerTexts
	 * 
	 * @param tokenizedText
	 * @return List<NerText>
	 */
	public List<NerText> extract(TokenizedText tokenizedText)
	{
		// use here the nerTimeEntity 
		return null;
	}

}
