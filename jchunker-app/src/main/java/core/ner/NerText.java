package core.ner;

import java.util.ArrayList;
import java.util.List;

public class NerText
{
	List<NameEntity> nameEntities;
	
	public NerText()
	{
		nameEntities = new ArrayList<NameEntity>();
	}
	
	public void add(NameEntity nameEntity)
	{
		nameEntities.add(nameEntity);
	}
	
	public List<NameEntity> getNameEntities()
	{
		return nameEntities;
	}
}
