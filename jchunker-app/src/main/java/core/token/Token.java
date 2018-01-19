package core.token;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlValue;

import core.Shared.MergedEntity;

@XmlRootElement(name="token")
@XmlSeeAlso({core.token.TokenizedText.class})
public class Token extends MergedEntity
{
    private int id;
    private String pos;
    private String posTag;
    private String lemma;
    private String word;

    public int getId() {
        return id;
    }

    @XmlAttribute(name="id")
    public void setId(int id) {
        this.id = id;
    }

    public String getPOS() 
    {
        return pos;
    }

    @XmlAttribute(name = "pos")
    public void setPOS(String POS) {
        this.pos = POS;
    }

    public String getLemma() {
        return lemma;
    }

    @XmlAttribute(name="lemma")
    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public String getPosTag()
    {
    	return posTag;
    }
    
    @XmlAttribute(name="partOfSpeechTag")
    public void setPosTag(String posTag)
    {
    	this.posTag = posTag;
    }
    
    public String getWord()
    {
    	return word;
    }
    
    @XmlValue
    public void setWord(String word)
    {
    	this.word = word;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", POS='" + pos + '\'' +
                ", lemma='" + lemma + '\'' +
                ", posTag='" + posTag + '\'' +
                ", word='" + word + '\'' +
                '}';
    }

}
