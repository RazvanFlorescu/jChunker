package utils;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;

import app.ApplicationMain;
import core.ner.NameEntity;
import core.ner.NerText;
import core.npc.NounPhrase;
import core.npc.NounPhraseChunkedText;
import org.apache.log4j.Logger;

public class GeneralUtils {
    private GeneralUtils(){}
    public static final Logger logger = Logger.getLogger(ApplicationMain.class);
    private static final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    private static final String outputFileName = "output.xml";


    public static void validateInputPath(String path){
        File inputFile = new File(path);
        if(!inputFile.exists() || !inputFile.isFile()) {
            logger.error("Error : Input file not found at location " + path);
            System.exit(0);
        }			// input file not found exception
    }

    public static void validateOutputPath(String path) {
        File outputFile = new File(path);
        if (path == null) {
            logger.error("Error : Output path is null!");
        }   // if given path is null

        if (!outputFile.isFile()) {

            File dir = outputFile.getParentFile();
            if (!dir.isDirectory()) {
                logger.error("Error : Output directory not found !");
                System.exit(1);             // output directory not found
            } else if (!path.substring(path.length() - 4).equals(".xml")) {
                logger.error("Error : Output file must have xml extension !");
                System.exit(2);
            }   //if outputpath is a full path and the given file name does not have an xml extension
        }
    }
    public static String outputPathGenerator(String destination){
        File outputFile = new File(destination);
        String timestampString = timestamp+"";
        String newTimestampString = timestampString.replaceAll("[-:. ]", "");
        if(outputFile.isDirectory()){
            destination  += outputFileName;
            destination  += newTimestampString;

        }	//if ouputpath is a directory construct the full path

        if(outputFile.isFile() && outputFile.exists()) {
            destination = destination.replace(".xml", "");
            destination += newTimestampString;
            destination += ".xml";
        } // if ouputpath is a file that exists add timestamp to the file name

        if(outputFile.isFile() && !outputFile.exists()) {
           destination = outputFile +"";
        } //    if file doesnt exist do nothing

        return destination;
    }

    public static NounPhrase getNpcByTokenId(NounPhraseChunkedText nounPhraseChunkedText, String tokenId){
        for(NounPhrase nP : nounPhraseChunkedText.getNounPhraseList()){
            if (nP.getTokenId().equals(tokenId)){
                return nP;
            }
        }
        return null;
    }

    public static List<NameEntity> getNersByTokenId(List<NerText> nerTexts, String tokenId){
        List<NameEntity> nameEntitiesResult = new ArrayList<NameEntity>() ;
        for(NerText nerText : nerTexts){
            for(NameEntity nE : nerText.getNameEntities()){
                if(nE.getTokenId().equals(tokenId)){
                    nameEntitiesResult.add(nE);
                }
            }
        }
        return nameEntitiesResult;
    }
}
