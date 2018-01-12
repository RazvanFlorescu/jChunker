package core.text;

import java.util.ArrayList;
import java.util.List;

import core.Shared.MergedEntity;
import core.ner.NameEntity;
import core.npc.NounPhrase;
import core.token.Token;

import javax.xml.bind.annotation.*;

@XmlSeeAlso({MergedEntity.class , Token.class, NounPhrase.class, NameEntity.class})
@XmlRootElement(name = "text")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MergedText
{
	

	public MergedText(){
		outputList = new ArrayList<>();
	}
	/**
	 * 
	 */
	/**
	 * 
	 */

	/**
	 * 
	 */

	public List<MergedEntity> outputList;


	public List<MergedEntity> getOutputList() {
		return this.outputList;
	}

	@XmlAnyElement(lax=true)
	public void setOutputList(List<MergedEntity> outputList) {
		this.outputList = outputList;
	}

}
