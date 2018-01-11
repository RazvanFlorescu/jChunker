package core.token;

import com.sun.xml.internal.txw2.annotation.XmlCDATA;
import core.Shared.MergedEntity;

import javax.xml.bind.annotation.*;

@XmlSeeAlso({core.token.TokenizedText.class})
@XmlRootElement(name = "tok")
public class Token extends MergedEntity
{
    private String id;
    private String partOfSpeech;
    private String partOfSpeechTag;
    private String lemma;
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

    public String getId() {
        return id;
    }

    @XmlAttribute(name="id")
    public void setId(String id) {
        this.id = id;
    }

    public String getpartOfSpeech()
    {
        return partOfSpeech;
    }

    @XmlAttribute(name = "partOfSpeech")
    public void setpartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getLemma() {
        return lemma;
    }

    @XmlAttribute(name="lemma")
    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public String getpartOfSpeechTag()
    {
    	return partOfSpeechTag;
    }

    @XmlAttribute(name="partOfSpeechTag")
    public void setpartOfSpeechTag(String partOfSpeechTag)
    {
    	this.partOfSpeechTag = partOfSpeechTag;
    }


    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", lemma='" + lemma + '\'' +
                '}';
    }

}
