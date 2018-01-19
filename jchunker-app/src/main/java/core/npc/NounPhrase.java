package core.npc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import core.Shared.MergedEntity;
import core.token.Token;

@XmlRootElement(name="nounPhrase")
public class NounPhrase extends MergedEntity
{
	private static final String LIST_DELIMITER = ",";
	private int tokenId;			// reprezinta id-ul token-ului principal (substantivul)
	private String idList	= "";	// reprezinta lista de id-uri de token-uri aflate in relatie cu substantivul
	
	public int getTokenId()
	{
		return tokenId;
	}
	
	@XmlAttribute(name="tokenId")
	public void setTokenId(int tokenId)
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
	public void addTokenToList(Token token)
	{
		if(idList.contains(Integer.toString(token.getId())))
			return;
		
		StringBuilder builder = new StringBuilder(idList);
		if(builder.length() == 0) // empty list
			builder.append(token.getId());
		else
			builder.append(LIST_DELIMITER + token.getId());
		
		idList = builder.toString();
	}
	
	public void addMainToken(Token token)
	{
		this.tokenId = token.getId();
	}
	
	/*
	 * 	OVERRIDED METHODS
	 */
	@Override
	public String toString()
	{
		return "NP[ TokenId= " + tokenId + ", IdList=" + idList + "]";
	}
}
