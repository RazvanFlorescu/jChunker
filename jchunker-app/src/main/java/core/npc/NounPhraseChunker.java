package core.npc;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import core.npc.regex.FileRegexReader;
import core.npc.regex.Matcher;
import core.npc.regex.Pattern;
import core.token.Token;
import core.token.TokenizedText;

public class NounPhraseChunker
{
	private static NounPhraseChunkedText npText = new NounPhraseChunkedText();
	private static Logger logger				= Logger.getLogger(NounPhraseChunker.class);
	public NounPhraseChunker() {}
	
	public static NounPhraseChunkedText chunk(TokenizedText tokenizedText)
	{
		logger.info("Building the list of nouns ...");
		List<NounPhrase> nounPhraseList = NpcUtils.buildNounList(tokenizedText.getTokens());
		logger.info("Noun list built.");
		
		logger.info("Building the regexes map ...");
		Map<String, List<String>> relationRegexesMap = buildRegexesMap();
		logger.info("The regexes map is built.");
		
		logger.info("Looping now through the regexes.");
		for(Map.Entry<String, List<String>> entry : relationRegexesMap.entrySet())
		{
			logger.info("Finding relations of type: " + entry.getKey());
			for(String regex : entry.getValue())
			{
				logger.info("The regex used now is: " + regex);
				Pattern pattern = new Pattern();
				pattern.compile(regex);
				Matcher matcher = pattern.matcher(tokenizedText.getTokens());
				if(matcher.find())
				{
					logger.info("MATCH FOUND!!!");
					NounPhrase np = NpcUtils.getNounPhrase(nounPhraseList, matcher.getMainTagToken());
					for(Token matchedToken : matcher.getMatchingTokens())
						np.addTokenToList(matchedToken);
				}
				else
					logger.info("No matching has been found for: " + regex);
			}
		}
		
		logger.info("Returning the found noun phrase list...");
		npText.setNounPhraseList(nounPhraseList);
		return npText;
	}

	private static Map<String, List<String>> buildRegexesMap()
	{
		FileRegexReader reader = new FileRegexReader();
		reader.process("D:\\facultate\\GithubRepositories\\jChunker\\jchunker-app\\src\\main\\resources\\regex");
		
		return reader.getRelationMap();
	}

}
