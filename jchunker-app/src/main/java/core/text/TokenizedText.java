package core.text;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import core.Token;

import java.util.ArrayList;
import java.util.List;

@XmlSeeAlso({core.Token.class})
@XmlRootElement
public class TokenizedText
{


    @XmlElement(name="tok")
    private List<Token> tokens;

    public TokenizedText(){
        tokens = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "TokenizedText{" +
                "tokens=" + tokens +
                '}';
    }
}
