package core.npc;

import java.util.ArrayList;
import java.util.List;

import core.token.Token;

public class NpcUtils
{
	private NpcUtils() {}
	
	public static List<NounPhrase> buildNounList(List<Token> tokenList)
	{
		List<NounPhrase> npList = new ArrayList<>();
		
		for(Token token : tokenList)
		{
			if(token.getPosTag().startsWith("N") || token.getPosTag().startsWith("n"))
			{
				NounPhrase np = new NounPhrase();
				np.addMainToken(token);
				npList.add(np);
			}
		}
		
		return npList;
	}
	
	public static List<NounPhrase> getNounPhrase(List<NounPhrase> npList, Token token)
	{
		List<NounPhrase> resultList = new ArrayList<>();
		for(NounPhrase np : npList)
		{
			if(np.getTokenId() == token.getId() || np.getIdList().contains(Integer.toString(token.getId())))
				resultList.add(np);
		}
		
		return resultList;
	}
}
