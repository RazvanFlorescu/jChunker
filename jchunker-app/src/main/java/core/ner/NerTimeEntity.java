package core.ner;

import java.io.File;
import java.io.IOException;

import core.token.Token;

public class NerTimeEntity implements Recognizer
{
	private File gazeteerFile;
	
	public NerTimeEntity(String gazeteerPath) throws IOException
	{
		this.gazeteerFile = new File(gazeteerPath);
		if(!this.gazeteerFile.canRead())
			throw new IOException("Cannot read file : " + gazeteerPath);
	}
	
	@Override
	public NameEntity recognize(Token token)
	{
		return null;
	}

}
