package app;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import core.ner.NerExtractor;
import core.ner.NerText;
import core.npc.NounPhraseChunkedText;
import core.npc.NounPhraseChunker;
import core.text.MergedText;
import core.token.TokenizedText;
import utils.GeneralUtils;
import utils.TextUtils;

public class ApplicationMain
{

	public static final Logger logger = Logger.getLogger(ApplicationMain.class);

	public static void main(String[] args)
	{

		BasicConfigurator.configure();
		if(args.length != 2){
			System.out.print(args.length);
			logger.error("Error : wrong number of arguments from command line! ");
			System.exit(0);
		}

		String source      = args[0];
		String destination = args[1];


		GeneralUtils.validateInputPath(source);
		GeneralUtils.validateOutputPath(destination);
		destination = GeneralUtils.outputPathGenerator(destination);
		//System.out.print(destination);
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
		System.out.print(tokenizedText.getTokens());
		/**
		 * Step 1. Noun Phrase Chucking
		 * 	At this step, NounPharseChunker consumes a sequence
		 * of <token> elements and produces <nounPhrase> elements
		 * <nounPhrase tokenId="" idList="" />
		 */
		NounPhraseChunkedText chunkedText = NounPhraseChunker.chunk(tokenizedText);
		
		/**
		 * Step 2. Name Entity Recognizer
		 * At this step, NounPharseChunker consumes a sequence
		 * of <token> elements <nameEntity> elements.
		 * <nameEntity tokenId="" type="" />
		 */
		NerExtractor  nerExtractor = null;
		List<NerText> nerTexts     = null;
		try
		{
			nerExtractor = new NerExtractor();
			nerTexts     = nerExtractor.extract(tokenizedText);
		} catch (IOException e)
		{
			logger.error("Step 2. Failed !", e);
			System.exit(1);
		}

		
		/**
		 * Step 3. Merge& Export
		 * At this step the merge of all sources is done and a new
		 * instance of MergedText is created. This instance must be
		 * marshaled in xml form.
		 * 
		 */
	/*	chunkedText = new NounPhraseChunkedText();
		NounPhrase nP = new NounPhrase();
		nP.setTokenId("1");
		nP.setIdList("1");
		nP.setContent("plm");

		chunkedText.getNounPhraseList().add(nP);

		nP = new NounPhrase();
		nP.setTokenId("2");
		nP.setIdList("3");
		nP.setContent("plm2");
		chunkedText.getNounPhraseList().add(nP);

		nP = new NounPhrase();
		nP.setTokenId("5");
		nP.setIdList("3");
		nP.setContent("plm3");
		chunkedText.getNounPhraseList().add(nP);

		NerText nT= new NerText();
		NameEntity nE = new NameEntity();
		nE.setTokenId("1");
		nE.setContent("asda");
		nT.getNameEntities().add(nE);

		 nE = new NameEntity();
		nE.setTokenId("2");
		nE.setContent("asda");
		nT.getNameEntities().add(nE);

		nE = new NameEntity();
		NerText nT2 = new NerText();
		nE.setTokenId("5");
		nE.setContent("asda");
		nT2.getNameEntities().add(nE);

		nE = new NameEntity();
		NerText nT3 = new NerText();
		nE.setTokenId("5");
		nE.setContent("asda");
		nT3.getNameEntities().add(nE);

		nE = new NameEntity();
		nerTexts = new ArrayList<NerText>();
		nerTexts.add(nT);
		nerTexts.add(nT2);
		nerTexts.add(nT3);
		*/  // user to test TextUtils.merge method
		MergedText mergedText = TextUtils.merge(tokenizedText, chunkedText, nerTexts);

		TextUtils.marshal(destination, mergedText);
		
	}
	
}
