package core.ner;

import javax.xml.bind.annotation.XmlRootElement;
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

	public void setNameEntities(List<core.ner.NameEntity> nameEntities) {
		this.nameEntities = nameEntities;
	}

	public List<NameEntity> getNameEntities()
	{
		return nameEntities;
	}


}
