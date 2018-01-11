package core.npc;

import core.Shared.MergedEntity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "nounPhrase")
public class NounPhrase extends MergedEntity
{
	private static final String LIST_DELIMITER = ",";
	private String tokenId 	= "";	// reprezinta id-ul token-ului principal (substantivul)
	private String idList	= "";	// reprezinta lista de id-uri de token-uri aflate in relatie cu substantivul
	
	public String getTokenId()
	{
		return tokenId;
	}
	private String content;
	public String getContent()
	{
		return content;
	}

	@XmlValue
	public void setContent(String word)
	{
		this.content = word;
	}
	@XmlAttribute(name="tokenId")
	public void setTokenId(String tokenId)
	{
		this.tokenId = tokenId;
	}
	
	public String getIdList()
	{
		return idList;
	}
	
	@XmlAttribute(name="idList")
	public void setIdList(String idList)
	{
		this.idList = idList;
	}

	/*
	 * 	ADDITIONAL METHODS
	 */
	public void addTokenToList(String id)
	{
		StringBuilder builder = new StringBuilder(idList);
		if(builder.length() == 0) // empty list
			builder.append(id);
		else
			builder.append(LIST_DELIMITER + id);
		
		idList = builder.toString();
	}
}
