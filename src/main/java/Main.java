import java.util.List;
import put.pl.NamedEntityParser;
import put.pl.SparkQLQueryExecutor;


public class Main {

    private static final String INPUT_FILE_NAME = "training/oke17task1Training.xml.ttl";

    public static void main(String[] args) throws Exception{
        String sentence = "Gottfried Wilhelm Leibniz was born in Leipzig in 1646 and attended the University of Leipzig from 1661-1666."; 
        List<String> entities = NamedEntityParser.parseAllEntities(sentence);
        SparkQLQueryExecutor.ExecuteQueryForEntities(entities);
    }
}
