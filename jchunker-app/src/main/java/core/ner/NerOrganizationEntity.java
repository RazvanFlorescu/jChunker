package core.ner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import core.token.Token;

public class NerOrganizationEntity implements Recognizer
{
    private File gazeteerFile;
	
	public NerOrganizationEntity(String gazeteerPath) throws IOException
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
	   resultExpectation.setType("organzation");
	   resultExpectation.setWord(token.getWord());
	   
	   try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(gazeteerFile.getAbsolutePath()), "UTF-8"));){
		   String line = br.readLine();
		   while(line!=null){
			   String [] wordAndAlias = line.split(":");
			   if(wordAndAlias[0].contains(token.getWord()) && wordAndAlias[0].replace(" ","").length()== token.getWord().replace(" ","").length()||
				  wordAndAlias[1].contains(token.getWord()) && wordAndAlias[1].replace(" ","").length()== token.getWord().replace(" ","").length()){
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
