package core.npc.regex.action;

import java.util.List;

/**
 * 	Utility class for custom-made regex pattern matching
 * @author Razvan
 *
 */
public class RegexUtils
{
	public static MatchingAction getActionFromList(ActionType actionType, List<MatchingAction> actionList)
	{
		for(MatchingAction action : actionList)
			if(action.actionType == actionType)
				return action;
		
		return null;
	}
	
	public static boolean arePosTagsEqual(String postag1, String postag2)
	{
		if(postag1.length() != postag2.length())
			return false;
		
		boolean areEqual = true;
		for(int index = 0; index < postag1.length(); index++)
		{
			if(postag1.charAt(index) == '.' || postag2.charAt(index) == '.' ||
				postag1.charAt(index) == '-' || postag2.charAt(index) == '-' ||
				postag1.charAt(index) == postag2.charAt(index))
				continue;
			else
				areEqual = false;
		}
		
		return areEqual;
	}
}
