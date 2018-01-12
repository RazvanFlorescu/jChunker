package core.npc.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class FileRegexReader
{
	private static final String FILE_PARSING_REGEX = ".*?\"(.*)?\".*?\\(rel_name (.*)?\\)\\)";
	
	
	// map which contains the relations and their respective regex-es
	private Map<String,List<String>> relationMap;
	private static final Logger logger = Logger.getLogger(FileRegexReader.class);
	
	public FileRegexReader()
	{
		relationMap = new HashMap<>();
	}
	
	/*
	 * 	PUBLIC METHODS
	 */
	public void process(String resourceFolder)
	{
		File rootFolder = new File(resourceFolder);
		if(!rootFolder.isDirectory())
		{	
			throw new IllegalArgumentException("The argument is not a valid folder name");
		}
		
		try
		{
			for(File file : rootFolder.listFiles())
			{
				if(!file.isDirectory())
					relationMap.putAll(getRelationFromFile(file));
			}
		}
		catch (IOException e)
		{
			logger.error("Error when parsing the files", e);
			return;
		}
	}

	public Map<String, List<String>> getRelationMap()
	{
		return relationMap;
	}
	
	/*
	 * 	PRIVATE METHODS
	 */
	private Map<? extends String, ? extends List<String>> getRelationFromFile(File file) throws IOException
	{
		Map<String,List<String>> resultMap = new HashMap<>();
		
		// I. Read the file
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		while(line != null)
		{
			// II. Parsing every line to find the regex used
			Pattern pattern = Pattern.compile(FILE_PARSING_REGEX);
			Matcher matcher = pattern.matcher(line);
			if(matcher.find())
			{
				// III. Insert the found regex into the map
				String relation = matcher.group(2);
				if(resultMap.get(relation) == null)
				{
					// LAZY INITIALIZATION
					List<String> regexList = new ArrayList<>();
					resultMap.put(relation, regexList);
				}
				resultMap.get(relation).add(matcher.group(1));
			}
			
			line = reader.readLine();
		}
		
		// close the reader
		reader.close();
		
		return resultMap;
	}
}
