package app;

import java.util.List;

import core.ChunkedText;
import core.NameEntityText;
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
		
		// 	This tokenized text is organized as an xml document
		// of the form <text>..tokens..</text> where tokens is a 
		// sequence of <tok> tags. A <tok> tag has three atributes
		// id, pos, and lem.
		// pos = PartOfSpeech
		// lem = Lemmatiser
		TokenizedText tokenizedText = TextUtils.unmarshal();

		//System.out.println(tokenizedText.toString());
                                     //  testing unmarshall method

		//TextUtils.marshal("src\\main\\resources\\inputFiles\\outputText.xml",tokenizedText);
                                    // testing marshall method
		/**
		 * Step 1. Noun Phrase Chunking
		 * 	At this step, NounPharseChunker consumes a sequence
		 * of <tok> elements and produces <np> elements
		 * 
		 * INFO: from what I see in the course, the tok tags get transformed
		 * into np tags. The question is, do we need after this step the tok tags ?
		 * INFO: we may also use the id atribute to link tok and np tags. I don't know
		 * exactly
		 * FIXME: (info) refactor the ChunkedText into NounPhraseChunkedText
		 * 
		 */
		ChunkedText chunkedText = NounPharseChunker.chunk(tokenizedText);
		
		/**
		 * Step 2. Name Entity Recognizer
		 * 	At this step, extractNer method consumes a text and produces 
		 * a sequence of <ne> elements.
		 * 
		 * INFO: maybe the input of it should be just the tokenizedText
		 * 	given the fact that what it does is just a look-up in the gazeeteer.
		 * If you use this option we will have an easyer life when doing the merge
		 * because you can use the tok id for the ne id thus making things simpler in
		 * the merge phase
		 */
		List<NameEntityText> chunkedTexts = extractNer(tokenizedText);
		
		
		/**
		 * Step 3. Merge& Export results
		 * 
		 * well, given the above hints, maybe the 
		 */
		//ChunkedText mergedText = Merger.merge(chunkedTexts);
		
		//TextUtils.marshal(destination, mergedText);
		
	}

	private static List<NameEntityText> extractNer(TokenizedText chunkedText)
	{
		// FIXME: (info) threadPool, cyclic barrier
		return null;
	}
	
}
