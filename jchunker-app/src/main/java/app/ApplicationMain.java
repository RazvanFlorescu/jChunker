package app;

import java.util.*;

import core.ChunkedText;
import core.TokenizedText;
import core.npc.NounPharseChunker;
import utils.Merger;
import utils.TextUtils;

public class ApplicationMain
{

	public static void main(String[] args)
	{
		// FIXME: (info) parametri de forma : ((dir)/)*{fileName}.{fileExtension}
		//String source      = args[0];
		//String destination = args[1];
		
		TokenizedText tokenizedText = TextUtils.unmarshal();

		//System.out.println(tokenizedText.toString());
                                     //  testing unmarshall method

		//TextUtils.marshal("src\\main\\resources\\inputFiles\\outputText.xml",tokenizedText);
                                    // testing marshall method
		/**
		 * Step 1. Noun Phrase Chunking
		 */
		ChunkedText chunkedText = NounPharseChunker.chunk(tokenizedText);
		
		/**
		 * Step 2. Name Entity Recognizer
		 */
		List<ChunkedText> chunkedTexts = extractNer(chunkedText);
		
		
		/**
		 * Step 3. Merge& Export results
		 */
		ChunkedText mergedText = Merger.merge(chunkedTexts);
		
		//TextUtils.marshal(destination, mergedText);
		
	}

	private static List<ChunkedText> extractNer(ChunkedText chunkedText)
	{
		// FIXME: (info) threadPool, cyclic barrier
		return null;
	}
	
}
