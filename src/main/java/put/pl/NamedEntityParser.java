package put.pl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream; 
import opennlp.tools.namefind.NameFinderME; 
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME; 
import opennlp.tools.tokenize.TokenizerModel; 
import opennlp.tools.util.Span;


public class NamedEntityParser {
    public enum EntityParserType {
        LOCATION, ORGANIZATION, PERSON;
    }
    private static String tokenizerPath = "openNLP/en-token.bin";

    public static List<String> parseAllEntities(String sentence) throws Exception{
        List<String> parsedEntities = new ArrayList<String>();
        for(EntityParserType parser: EntityParserType.values()){
            parsedEntities.addAll(parseSpecificEntities(sentence, parser));
        }
        return parsedEntities;
    }

    public static List<String> parseSpecificEntities(String sentence, EntityParserType parserType) throws Exception{;
        List<String> recognizedEntities = new ArrayList<String>();
        InputStream inputStreamNameFinder = new FileInputStream(getParserFilePath(parserType));       
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
        NameFinderME nameFinder = new NameFinderME(model);

        InputStream inputStreamTokenizer = new FileInputStream(tokenizerPath);
        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
        TokenizerME tokenizer = new TokenizerME(tokenModel); 
        String tokens[] = tokenizer.tokenize(sentence); 
        Span nameSpans[] = nameFinder.find(tokens); 
       
        for(Span s: nameSpans) {
            List<String> combined = new ArrayList<String>();
            for(int i = s.getStart(); i < s.getEnd(); i++)
                combined.add(tokens[i]);;
            recognizedEntities.add(String.join(" ", combined));
        } 
        return recognizedEntities;
    }

    private static String getParserFilePath(EntityParserType parserType){
        switch(parserType){
            case LOCATION:      
                return "openNLP/en-ner-location.bin";
            case ORGANIZATION:  
                return "openNLP/en-ner-organization.bin";
            case PERSON:        
                return "openNLP/en-ner-person.bin"; 
            default:
                return null;       
        }
    }
}
