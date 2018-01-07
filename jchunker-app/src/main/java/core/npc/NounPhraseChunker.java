package core.npc;

import core.token.TokenizedText;

public class NounPhraseChunker
{
	private static NounPhraseChunkedText npText = new NounPhraseChunkedText();
	private NounPhraseChunker() {}
	
	public static NounPhraseChunkedText chunk(TokenizedText tokenizedText)
	{
		return null;
	}

	public NounPhraseChunkedText getNpText()
	{
		return npText;
	}

}
