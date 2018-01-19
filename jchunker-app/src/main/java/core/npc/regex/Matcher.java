package core.npc.regex;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import core.npc.regex.action.ActionType;
import core.npc.regex.action.MatchingAction;
import core.npc.regex.action.RegexUtils;
import core.token.Token;
import utils.GeneralUtils;

public final class Matcher
{
	private List<Token> tokenList;
	private List<MatchingAction> actionList;
	private int startingMatchingIndex;
	
	public Matcher(List<MatchingAction> actionList, List<Token> tokenList)
	{
		this.actionList = actionList;
		this.tokenList 	= tokenList;
	}
	
	public boolean find()
	{
		List<Token> tokenListCopy;
		boolean hasFound;
		for(int index = startingMatchingIndex; index < tokenList.size(); index++)
		{
			hasFound = true;
			tokenListCopy = tokenList.subList(index, tokenList.size());
			for(MatchingAction action : actionList)
			{
				if(!action.match(tokenListCopy))
				{
					hasFound = false;
					RegexUtils.clearMatchedTokens(actionList);
					break;
				}
				
				tokenListCopy = tokenListCopy.subList(action.getNumberOfTokenMatched(), tokenListCopy.size());
			}
			
			if(hasFound)
			{
				int nextMatchingId = GeneralUtils.getBiggestIdFromTokenList(actionList.get(actionList.size() - 1).getMatchedTokens());
				if(startingMatchingIndex == nextMatchingId) // this means that nothing has changed
					startingMatchingIndex = tokenList.size(); // in order to exit from loop
				else
					startingMatchingIndex = nextMatchingId;
				
				return true;
			}
		}
		
		return false;
	}
	
	
	public Token getMainTagToken()
	{
		List<Token> matchedTokens = RegexUtils.getActionFromList(ActionType.MAIN_POSTAG, actionList).getMatchedTokens();
		if(matchedTokens == null || matchedTokens.isEmpty())
			return null;
		
		return matchedTokens.get(0);
	}
	
	public List<Token> getMatchingTokens()
	{
		Set<Token> tokenSet = new LinkedHashSet<>();
		for(MatchingAction action : actionList)
		{
			tokenSet.addAll(action.getMatchedTokens());
		}
		
		tokenSet.remove(getMainTagToken());
		
		return new LinkedList<>(tokenSet);
	}
	
	public void clearMatchedTokens()
	{
		RegexUtils.clearMatchedTokens(actionList);
	}
}
