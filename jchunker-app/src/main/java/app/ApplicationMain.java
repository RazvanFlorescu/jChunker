package app;

import java.util.List;

import core.NerText;
import core.ner.NerExtractor;
import core.npc.NounPharseChunker;
import core.text.MergedText;
import core.text.NounPharseChunkedText;
import core.text.TokenizedText;
import utils.TextUtils;

public class ApplicationMain
{

	public static void main(String[] args)
	{
		// FIXME: (info) parametri de forma : ((dir)/)*{fileName}.{fileExtension}
		String source      = args[0];
		String destination = args[1];

		/**
		 * TokenizedText will contain a list of Tokens
		 * Token is a JAXB annotated class with the 
		 * following fields :
		 * 	id           - id of the token
		 * 	partOfSpeech - what part of speech is the word
		 * 	lemma        - root of the word
		 * 	word		 - 
		 * The format of the source is :
		 * <text>
		 * 	<token id=".." partOfSpeech="" lemma="">#value#</token>
		 * 	..
		 * </text>
		 */
		TokenizedText tokenizedText = TextUtils.unmarshal(source);

		/**
		 * Step 1. Noun Phrase Chucking
		 * 	At this step, NounPharseChunker consumes a sequence
		 * of <token> elements and produces <nounPhrase> elements
		 * <nounPhrase tokenId="" idList="" />
		 */
		NounPharseChunkedText chunkedText = NounPharseChunker.chunk(tokenizedText);
		
		/**
		 * Step 2. Name Entity Recognizer
		 * At this step, NounPharseChunker consumes a sequence
		 * of <token> elements <nameEntity> elements.
		 * <nameEntity tokenId="" type="" />
		 */
		List<NerText> nerTexts = NerExtractor.extract(tokenizedText);
		
		
		/**
		 * Step 3. Merge& Export
		 * At this step the merge of all sources is done and a new
		 * instance of MergedText is created. This instance must be
		 * marshaled in xml form.
		 * 
		 */
		MergedText mergedText = TextUtils.merge(tokenizedText, chunkedText, nerTexts);
		
		TextUtils.marshal(destination, mergedText);
		
	}
	
}
