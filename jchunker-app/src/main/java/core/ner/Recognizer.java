package core.ner;

import core.token.Token;

public interface Recognizer
{
	public NameEntity recognize(Token token);
}
