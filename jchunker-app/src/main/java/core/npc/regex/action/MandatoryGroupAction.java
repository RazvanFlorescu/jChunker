package core.npc.regex.action;

import java.util.List;

import core.token.Token;

public class MandatoryGroupAction extends MatchingAction
{
	public MandatoryGroupAction()
	{
		super(ActionType.MANDATORY_GROUP);
	}

	@Override
	public boolean match(List<Token> tokenList)
	{		
		if(childrenActionList.isEmpty())
			return true;
		else
		{
			for(MatchingAction child : childrenActionList)
			{
				if(!child.match(tokenList))
					return false;
				
				matchedTokens.addAll(child.getMatchedTokens());
			}
			return true;
		}

	}

}
