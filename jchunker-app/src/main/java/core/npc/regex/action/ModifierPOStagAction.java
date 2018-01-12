package core.npc.regex.action;

import java.util.List;

import core.token.Token;

public class ModifierPOStagAction extends MatchingAction
{
	private String posTag;
	
	public ModifierPOStagAction(String posTag)
	{
		super(ActionType.MODIFIER_POSTAG);
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
