package test.artefacts;

import core.Shared.MergedEntity;
import core.ner.NerText;
import core.npc.NounPhrase;
import core.npc.NounPhraseChunkedText;
import core.text.MergedText;
import core.token.Token;
import core.token.TokenizedText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raluca.plugariu on 1/12/2018.
 */
public class TextUtilsArtefacts {

    public static TokenizedText createTokenizedText() {
        List<Token> tokens = new ArrayList<>();
        Token token1 = new Token();
        Token token2 = new Token();

        token1.setId("1");
        token1.setLemma("apoi");
        token1.setpartOfSpeech("adverb");

        token2.setId("2");
        token2.setLemma("ieși");
        token2.setpartOfSpeech("verb predicative");

        tokens.add(token1);
        tokens.add(token2);

        TokenizedText tokenizedText = new TokenizedText();
        tokenizedText.setTokens(tokens);
        return tokenizedText;

    }
    public static MergedText createMergedText(){
        MergedText mergedText = new MergedText();
        MergedEntity merged1 = new MergedEntity();

        MergedEntity merged2 = new MergedEntity();


        List<MergedEntity> outputList = new ArrayList<>();
        outputList.add(merged1);
        outputList.add(merged2);


        mergedText.setOutputList(outputList);
        return mergedText;

    }


    public static NounPhraseChunkedText createNounPhraseChunkedText(){
        NounPhraseChunkedText npc = new NounPhraseChunkedText();
        List<NounPhrase> npList = new ArrayList<>();
        NounPhrase np1 = new NounPhrase();
        NounPhrase np2 = new NounPhrase();
        np1.setContent("apoi");
        np1.setIdList("1");
        np1.setTokenId("1");

        np2.setContent("ieși");
        np2.setIdList("1");
        np2.setTokenId("2");

        npList.add(np1);
        npList.add(np2);

        npc.setNounPhraseList(npList);
        return  npc;

    }

    public static List<NerText> createnerTextList(){
        List<NerText> nerList = new ArrayList<>();
        NerText ner1 = new NerText();
        NerText ner2 = new NerText();

        nerList.add(ner1);
        nerList.add(ner2);
        return nerList;
    }
}
