package core.npc;

import core.Shared.MergedEntity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="npText")
public class NounPhraseChunkedText extends MergedEntity
{
	private List<NounPhrase> nounPhraseList;

	public NounPhraseChunkedText()
	{
		nounPhraseList = new ArrayList<>();
	}
	
	public List<NounPhrase> getNounPhraseList()
	{
		return nounPhraseList;
	}
	
	@XmlElement(name="nounPhrase")
	public void setNounPhraseList(List<NounPhrase> nounPhraseList)
	{
		this.nounPhraseList = nounPhraseList;
	}
	
	/*
	 * 	ADDITIONAL METHODS
	 */
	public void addNounPhrase(NounPhrase np)
	{
		nounPhraseList.add(np);
	}
}
