package test.npc;

import core.ner.NerText;
import core.npc.NounPhraseChunkedText;
import core.npc.NounPhraseChunker;
import core.token.TokenizedText;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import test.artefacts.TextUtilsArtefacts;

import java.util.List;

public class NounPhraseChunkerTest {

    private NounPhraseChunker nounPhraseChunker;
    private NounPhraseChunkedText npctext;


    @Test
    public void chunktTest() {
        //when
        NounPhraseChunkedText npcTest = TextUtilsArtefacts.createNounPhraseChunkedText();
        TokenizedText tokenizedText = TextUtilsArtefacts.createTokenizedText();

        //then
        NounPhraseChunkedText npc = nounPhraseChunker.chunk(tokenizedText);
        Assertions.assertThat(npc.getNounPhraseList().get(0).getIdList()).isEqualTo(npcTest.getNounPhraseList().get(0).getIdList());
//        Assertions.assertThat(npc.getNounPhraseList().get(0).getContent()).isEqualTo(npcTest.getNounPhraseList().get(0).getContent());
        Assertions.assertThat(npc.getNounPhraseList().get(0).getTokenId()).isEqualTo(npcTest.getNounPhraseList().get(0).getTokenId());

    }

}
