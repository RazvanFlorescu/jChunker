package core.token;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import java.util.ArrayList;
import java.util.List;

@XmlSeeAlso({core.token.Token.class})
@XmlRootElement(name = "text")
public class TokenizedText
{

    private List<Token> tokens;

    public TokenizedText(){
        tokens = new ArrayList<>();
    }
    
    @XmlElement(name="tok")
    public void setTokens(List<Token> tokens)
    {
    	this.tokens = tokens;
    }

    public List<Token> getTokens()
    {
    	return tokens;
    }

    @Override
    public String toString() {
        return "TokenizedText{" +
                "tokens=" + tokens +
                '}';
    }


}
