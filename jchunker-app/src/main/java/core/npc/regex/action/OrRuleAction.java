package core.npc.regex.action;

import java.util.List;

import core.token.Token;

public class OrRuleAction extends MatchingAction
{
	private String leftMember;
	private String rightMember;
	
	public OrRuleAction(String leftMember, String rightMember)
	{
		super(ActionType.OR_RULE_GROUP);
		this.leftMember 	= leftMember;
		this.rightMember 	= rightMember;
	}

	@Override
	public boolean match(List<Token> tokenList)
	{
		if(RegexUtils.arePosTagsEqual(tokenList.get(getNumberOfTokenMatched()).getPosTag().toUpperCase(),leftMember.toUpperCase()) ||
			RegexUtils.arePosTagsEqual(tokenList.get(getNumberOfTokenMatched()).getPosTag().toUpperCase(),rightMember.toUpperCase()))
		{
			matchedTokens.add(tokenList.get(getNumberOfTokenMatched()));
			return true;
		}
		
		return false;
	}

}
