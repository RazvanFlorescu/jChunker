package core.ner;

import core.Shared.MergedEntity;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "nameEntity")
public class NameEntity  extends MergedEntity
{
	private String tokenId;

    public String getTokenId() {
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
    @XmlAttribute(name = "tokenId")
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
