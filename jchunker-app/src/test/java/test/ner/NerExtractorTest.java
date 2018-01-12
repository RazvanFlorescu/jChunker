package test.ner;

import core.ner.NerExtractor;
import core.ner.NerText;
import core.token.TokenizedText;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import test.artefacts.TextUtilsArtefacts;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class NerExtractorTest {
    private NerExtractor nerText;
    private TokenizedText tokenizedText;


    @Test
    public void extractTest() {
        //when
        List<NerText> nerListTest = TextUtilsArtefacts.createnerTextList();
        TokenizedText tokenizedText = TextUtilsArtefacts.createTokenizedText();

        //then
        List<NerText> nerList = nerText.extract(tokenizedText);
        Assertions.assertThat(nerList.get(0).getNameEntities().get(0).getId()).isEqualTo(nerListTest.get(0).getNameEntities().get(0).getId());
        Assertions.assertThat(nerList.get(0).getNameEntities().get(0).getType()).isEqualTo(nerListTest.get(0).getNameEntities().get(0).getType());
        Assertions.assertThat(nerList.get(0).getNameEntities().get(0).getWord()).isEqualTo(nerListTest.get(0).getNameEntities().get(0).getWord());

    }




}
