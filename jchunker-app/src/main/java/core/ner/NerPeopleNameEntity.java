package core.ner;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import core.token.Token;

public class NerPeopleNameEntity implements Recognizer
{
    private File gazeteerFile;
	
	public NerPeopleNameEntity(String gazeteerPath) throws IOException
	{
		this.gazeteerFile = new File(gazeteerPath);
		if(!this.gazeteerFile.canRead())
			throw new IOException("Cannot read file : " + gazeteerPath);
	}
	
	public String textTokenization(String text) throws IOException{
		  List<String>finalText=new ArrayList<>();
		  List<String>tokenizedText=new ArrayList<>();
		   
	      String [] idAndWord = text.split("<tok id=|</tok>");
 
       for(String str:idAndWord )
       {
    	   
    	   String [] words=str.split(">");
    	   String word = null;
    	   int count=0;
    	   
    	   for (String string : words) {
				if(count==1){
					word=string;
				}
			    count++;
		   }
    	   
    	   try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(gazeteerFile.getAbsolutePath()), "UTF-8"));) 
    	   {
	           StringBuilder sb = new StringBuilder();
		       String line = br.readLine();
		    	    
			   int flag=0;//verificam daca nu s-a gasit nimic in gazeetere
			   if(str.contains("<text>")||str.contains("</text>"))
			   {
					  finalText.add(str);
					  flag=1;
			   }
		       while (line != null && !(str.contains("<text>")||str.contains("</text>")))
		       {
				     String compareStr=line.replace(" ","");	    
				     if(word!=null)
				     {
		        		 if(word.contains(compareStr) )
		        		 {
			    	    	  String format = "<ne id="+str+"</ne>";
			    	    	  finalText.add(format);
			    	    	  flag=1;
			    	    	  break;  
			    	      }
				     }
			       line =br.readLine();
		        }	
			      if(flag==0 && str.length()!=0)
			      {
		    		  String format = "<tok id="+str+"</tok>";
					  finalText.add(format);
			      }
	     }
		    
       }  
	StringBuilder finalString = new StringBuilder("");      
    for (String string : finalText) {
		finalString.append(string);
	}

	   
    return finalString.toString();
	}

	@Override
	public NameEntity recognize(Token token)
	{
		   NameEntity resultExpectation = new NameEntity();
		   resultExpectation.setId(token.getId());
		   resultExpectation.setType("peopleName");
		   resultExpectation.setWord(token.getWord());
		   
		   try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(gazeteerFile.getAbsolutePath()), "UTF-8"));){
			   String line = br.readLine();
			   while(line!=null){
				   if(line.contains(token.getWord()) && line.replace(" ","").length()== token.getWord().replace(" ","").length()){
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
   


