package core.npc.regex.action;

import java.util.LinkedList;
import java.util.List;

import core.token.Token;

public abstract class MatchingAction
{
	protected List<MatchingAction> childrenActionList;
	protected ActionType actionType;
	protected List<Token> matchedTokens;
	
	// Method to be implemented
	public abstract boolean match(List<Token> token);
	
	/*
	 * 	CONSTRUCTOR
	 */
	public MatchingAction(ActionType actionType)
	{
		matchedTokens		= new LinkedList<>();
		childrenActionList 	= new LinkedList<>();
		this.actionType 	= actionType;
	}
	
	/*
	 * 	GETTERS
	 */
	public List<MatchingAction> getChildrenActionList()
	{
		return childrenActionList;
	}

	public int getNumberOfTokenMatched()
	{
		return matchedTokens.size();
	}

	public List<Token> getMatchedTokens()
	{
		return matchedTokens;
	}
}
