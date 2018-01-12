package core.npc.regex.action;

/**
 * 	Class containing factory methods for creating {@link MatchingAction} instances.
 * 	@author Razvan
 *
 */
public class MatchingActionFactory
{
	public static MatchingAction createMainPosTagAction(String posTag)
	{
		return new MainPOStagAction(posTag);
	}
	
	public static MatchingAction createModifierPosTagAction(String posTag)
	{
		return new ModifierPOStagAction(posTag);
	}
	
	public static MatchingAction createMandatoryGroupAction()
	{
		return new MandatoryGroupAction();
	}
	
	public static MatchingAction createOptionalGroupAction()
	{
		return new OptionalGroupAction();
	}
	
	public static MatchingAction createOrRuleAction(String leftMember, String rightMember)
	{
		return new OrRuleAction(leftMember, rightMember);
	}
	
	public static MatchingAction createPlainPostagAction(String posTag)
	{
		return new PlainPosTagAction(posTag);
	}
}	
