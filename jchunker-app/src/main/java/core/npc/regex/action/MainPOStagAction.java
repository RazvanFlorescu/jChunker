package core.npc.regex.action;

import java.util.List;

import core.token.Token;

public class MainPOStagAction extends MatchingAction
{
	private String posTag;

	public MainPOStagAction(String posTag)
	{
		super(ActionType.MAIN_POSTAG);
		this.posTag = posTag;
	}

	@Override
	public boolean match(List<Token> tokenList)
	{
		if(RegexUtils.arePosTagsEqual(tokenList.get(getNumberOfTokenMatched()).getPosTag().toUpperCase(),posTag.toUpperCase()))
		{
			matchedTokens.add(tokenList.get(getNumberOfTokenMatched()));
			return true;
		}
		return false;
	}
}
