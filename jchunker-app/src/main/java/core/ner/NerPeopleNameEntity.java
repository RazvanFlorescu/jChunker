package core.ner;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NerPeopleNameEntity
{
	private String gazetteerPath;
	
	public NerPeopleNameEntity(String gazetteerPath){
		this.gazetteerPath=gazetteerPath;
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
    	   
    	   try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(gazetteerPath), "UTF-8"));) 
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
   
}

