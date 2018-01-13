package test.ner;

import core.ner.NerExtractor;
import core.ner.NerText;
import core.token.TokenizedText;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.artefacts.TextUtilsArtefacts;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class NerExtractorTest {
    @Mock
    private NerExtractor nerText;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void extractTest() {

        List<NerText> nerListTest = TextUtilsArtefacts.createnerTextList();
        TokenizedText tokenizedText = TextUtilsArtefacts.createTokenizedText();

        when(nerText.extract(any(TokenizedText.class))).thenReturn(nerListTest);

        assertEquals(nerText.extract(tokenizedText),nerListTest);

    }




}
