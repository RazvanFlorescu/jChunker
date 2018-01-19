package core.npc.regex.action;

import java.util.List;

import core.token.Token;

public class PlainPosTagAction extends MatchingAction
{
	private String posTag;
	
	public PlainPosTagAction(String posTag)
	{
		super(ActionType.PLAIN_POSTAG);
		this.posTag = posTag;
	}

	@Override
	public boolean match(List<Token> tokenList)
	{
//        System.out.print(tokenList.size());
		if(tokenList.size() == getNumberOfTokenMatched())
			return false;
		
		if(RegexUtils.arePosTagsEqual(tokenList.get(getNumberOfTokenMatched()).getPosTag().toUpperCase(),posTag.toUpperCase()))
		{
			matchedTokens.add(tokenList.get(getNumberOfTokenMatched()));
			return true;
		}
		return false;
	}

}
