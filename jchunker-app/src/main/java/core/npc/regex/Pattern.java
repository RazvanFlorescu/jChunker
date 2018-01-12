package core.npc.regex;

import java.util.LinkedList;
import java.util.List;

import core.npc.regex.action.MatchingAction;
import core.npc.regex.action.MatchingActionFactory;
import core.token.Token;

/**
 * Pattern class built for POS Tags regexes
 * @author Razvan
 *
 */
public final class Pattern
{
	/**
	 *  Used for matching sequences of type "1:Ncfpry"
	 */
	private static final String IDENTIFY_MAIN_POSTAG_REGEX 		= "1:([^ ]*)";
	
	/**
	 * 	Used for matching sequences of type "2:Afpfpn"
	 */
	private static final String IDENTIFY_MODIFIER_POSTAG_REGEX	= "2:([^ ]*)";
	
	/**
	 * 	Used for matching sequences of type "(TIMSR NCMS.N){1}"
	 */
	private static final String IDENTIFY_MANDATORY_GROUP_REGEX	= "\\((.*?)\\)\\{1\\}";
	
	/**
	 * 	Used for matching sequences of type "(TIMSR NCMS.N)?"
	 */
	private static final String IDENTIFY_OPTIONAL_GROUP_REGEX	= "\\((.*?)\\)\\?";
	
	/**
	 * 	Used for matching sequences of type "TIMSR|NCMS.N"
	 */
	private static final String IDENTIFY_OR_RULE_REGEX			= "([^ \\|]*)\\|([^ ]*)";

	/**
	 * 	Used for matching a postag: NCFSRN, NCMS.N etc..
	 */
	private static final String PLAIN_POSTAG_REGEX				= "([^ \\|\\(\\)\\{\\}\\?12:]*)";
	
	
	private List<MatchingAction> actionList;
	/*
	 *	At compilation time, the pattern should build a list of actions
	 *	which needs to be followed by the matcher
	 */
	public void compile(String regex)
	{
		actionList = buildActionList(regex);
	}
	
	public Matcher matcher(List<Token> tokenList)
	{
		return new Matcher(actionList,tokenList);
	}
	
	
	private List<MatchingAction> buildActionList(String regex)
	{
		List<MatchingAction> resultList = new LinkedList<>();
		java.util.regex.Pattern pattern;
		java.util.regex.Matcher matcher, innerMatcher;
		MatchingAction action;
		
		pattern = java.util.regex.Pattern.compile(IDENTIFY_MAIN_POSTAG_REGEX + "|" + IDENTIFY_MODIFIER_POSTAG_REGEX + "|"
									+ IDENTIFY_MANDATORY_GROUP_REGEX + "|" + IDENTIFY_OPTIONAL_GROUP_REGEX + "|"
									+ IDENTIFY_OR_RULE_REGEX + "|" + PLAIN_POSTAG_REGEX);
		matcher = pattern.matcher(regex);
		
		while(matcher.find())
		{
			pattern = java.util.regex.Pattern.compile(IDENTIFY_MAIN_POSTAG_REGEX);
			innerMatcher = pattern.matcher(matcher.group());
			if(innerMatcher.find())
			{
				action = MatchingActionFactory.createMainPosTagAction(innerMatcher.group(1));
				resultList.add(action);
				continue;
			}
			
			pattern = java.util.regex.Pattern.compile(IDENTIFY_MODIFIER_POSTAG_REGEX);
			innerMatcher = pattern.matcher(matcher.group());
			if(innerMatcher.find())
			{
				action = MatchingActionFactory.createModifierPosTagAction(innerMatcher.group(1));
				resultList.add(action);
				continue;
			}
			
			pattern = java.util.regex.Pattern.compile(IDENTIFY_MANDATORY_GROUP_REGEX);
			innerMatcher = pattern.matcher(matcher.group());
			if(innerMatcher.find())
			{
				action = MatchingActionFactory.createMandatoryGroupAction();
				resultList.add(action);
				
				// recursive action
				action.getChildrenActionList().addAll(buildActionList(innerMatcher.group(1)));
				continue;
			}
			
			pattern = java.util.regex.Pattern.compile(IDENTIFY_OPTIONAL_GROUP_REGEX);
			innerMatcher = pattern.matcher(matcher.group());
			if(innerMatcher.find())
			{
				action = MatchingActionFactory.createOptionalGroupAction();
				resultList.add(action);
				
				// recursive action
				action.getChildrenActionList().addAll(buildActionList(innerMatcher.group(1)));
				continue;
			}
			
			pattern = java.util.regex.Pattern.compile(IDENTIFY_OR_RULE_REGEX);
			innerMatcher = pattern.matcher(matcher.group());
			if(innerMatcher.find())
			{
				action = MatchingActionFactory.createOrRuleAction(innerMatcher.group(1), innerMatcher.group(2));
				resultList.add(action);
				continue;
			}
			
			// if we came until here, it means that we have a plain posTag
			pattern = java.util.regex.Pattern.compile(PLAIN_POSTAG_REGEX);
			innerMatcher = pattern.matcher(matcher.group());
			if(innerMatcher.find())
			{
				if(!innerMatcher.group(1).equals(""))
				{
					action = MatchingActionFactory.createPlainPostagAction(innerMatcher.group(1));
					resultList.add(action);
				}
			}
		}	
		
		return resultList;
	}
}
