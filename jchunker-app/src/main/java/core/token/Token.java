package core.token;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({core.token.TokenizedText.class})
public class Token {
    private int id;
    private String POS;
    private String lemma;

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getPOS() {
        return POS;
    }

    @XmlAttribute(name = "POS")
    public void setPOS(String POS) {
        this.POS = POS;
    }

    public String getLemma() {
        return lemma;
    }

    @XmlAttribute
    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", POS='" + POS + '\'' +
                ", lemma='" + lemma + '\'' +
                '}';
    }
}
