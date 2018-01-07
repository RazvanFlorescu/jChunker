package core.npc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="npText")
public class NounPhraseChunkedText
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
	
	@XmlAttribute(name="nounPhrase")
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
