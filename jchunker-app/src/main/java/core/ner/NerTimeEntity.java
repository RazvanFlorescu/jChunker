package core.ner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
		 NameEntity resultExpectation = new NameEntity();
		   resultExpectation.setId(token.getId());
		   resultExpectation.setType("time");
		   resultExpectation.setWord(token.getWord());
		   
		   try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(gazeteerFile.getAbsolutePath()), "UTF-8"));){
			   String line = br.readLine();
			   while(line!=null){
				   if(line.contains(token.getWord().toLowerCase()) && line.toLowerCase().replace(" ","").length()== token.getWord().toLowerCase().replace(" ","").length()){
					   return resultExpectation;
				   }
				   line=br.readLine();
			   }
			   
		   } catch (IOException x) {
			    System.err.format("IOException: %s%n", x);
		   }
	   		
			
			return null;
	}
}


