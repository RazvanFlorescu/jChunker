package test.npc;

import core.ner.NerText;
import core.npc.NounPhrase;
import core.npc.NounPhraseChunkedText;
import core.npc.NounPhraseChunker;
import core.token.TokenizedText;
import org.assertj.core.api.Assertions;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import test.artefacts.TextUtilsArtefacts;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import org.powermock.api.mockito.PowerMockito;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NounPhraseChunker.class, NounPhraseChunkedText.class})
public class NounPhraseChunkerTest{

    @Test
    public void chunktTest() {

        PowerMockito.mockStatic(NounPhraseChunker.class);
        MockitoAnnotations.initMocks(this);

        NounPhraseChunkedText npcTest = TextUtilsArtefacts.createNounPhraseChunkedText();
        TokenizedText tokenizedText = TextUtilsArtefacts.createTokenizedText();


        when(NounPhraseChunker.chunk(tokenizedText)).thenReturn(npcTest);

        assertEquals(NounPhraseChunker.chunk(tokenizedText),npcTest);



    }

}
