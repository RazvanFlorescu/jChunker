package core.npc.regex;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import core.npc.regex.action.ActionType;
import core.npc.regex.action.MatchingAction;
import core.npc.regex.action.RegexUtils;
import core.token.Token;

public final class Matcher
{
	private List<Token> tokenList;
	private List<MatchingAction> actionList;
	
	public Matcher(List<MatchingAction> actionList, List<Token> tokenList)
	{
		this.actionList = actionList;
		this.tokenList 	= tokenList;
	}
	
	public boolean find()
	{
		List<Token> tokenListCopy = new LinkedList<>(tokenList);
		
		for(MatchingAction action : actionList)
		{
			if(!action.match(tokenListCopy))
				return false;
			
			tokenListCopy = tokenListCopy.subList(action.getNumberOfTokenMatched(), tokenListCopy.size());
		}
		
		return true;
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
}
