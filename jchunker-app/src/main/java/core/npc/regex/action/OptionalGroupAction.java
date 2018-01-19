package core.npc.regex.action;

import java.util.LinkedList;
import java.util.List;

import core.token.Token;

public class OptionalGroupAction extends MatchingAction
{
	public OptionalGroupAction()
	{
		super(ActionType.OPTIONAL_GROUP);
	}

	@Override
	public boolean match(List<Token> tokenList)
	{
		if(childrenActionList.isEmpty())
			return true;
		else
		{
			boolean isMatchingAllChilds	  = true;
			for(MatchingAction child : childrenActionList)
			{
				if(!child.match(tokenList))
					isMatchingAllChilds = false;
			}
			
			if(isMatchingAllChilds)
			{
				matchedTokens.addAll(getChildMatchedTokens());
			}
			else
				RegexUtils.clearMatchedTokens(childrenActionList);
			
			return true;
		}
	}

	private List<? extends Token> getChildMatchedTokens()
	{
		List<Token> resultList = new LinkedList<>();
		for(MatchingAction action : childrenActionList)
			resultList.addAll(action.getMatchedTokens());
		
		return resultList;
	}

}
