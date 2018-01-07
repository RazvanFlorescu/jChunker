package core.text;

import java.util.List;

import core.ner.NameEntity;
import core.npc.NounPhrase;
import core.token.Token;

public class MergedText
{
	
	public MergedText()
	{
		
	}
	
	/**
	 * 
	 */
	private List<List<NameEntity>> nerLists;
	
	/**
	 * 
	 */
	private List<Token> tokens;
	
	/**
	 * 
	 */
	private List<NounPhrase> nounPhrases;
	
	
	public List<List<NameEntity>> getNerLists()
	{
		return nerLists;
	}

	public void setNerLists(List<List<NameEntity>> nerLists)
	{
		this.nerLists = nerLists;
	}

	public List<Token> getTokens()
	{
		return tokens;
	}

	public void setTokens(List<Token> tokens)
	{
		this.tokens = tokens;
	}

	public List<NounPhrase> getNounPhrases()
	{
		return nounPhrases;
	}

	public void setNounPhrases(List<NounPhrase> nounPhrases)
	{
		this.nounPhrases = nounPhrases;
	}

}
