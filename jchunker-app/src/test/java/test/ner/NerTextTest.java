package test.ner;


import core.ner.NerText;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.artefacts.TextUtilsArtefacts;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
public class NerTextTest {
    private NerText nerText;


    @Test
    public void nerTextConstructorTest(){

        NerText nerText = new NerText();

        assertTrue(nerText.getNameEntities().isEmpty());

    }

    @Test
    public void nerTextSetterTest(){
        //when
        NerText nerText = new NerText();

        //then
        nerText.setNameEntities(TextUtilsArtefacts.createNameEntityList());
        assertThat(nerText.getNameEntities().get(0).getId()).isEqualTo(TextUtilsArtefacts.createNameEntityList().get(0).getId());

    }




}
