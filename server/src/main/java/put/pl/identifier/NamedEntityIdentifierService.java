package put.pl.identifier;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
* Bean containing parser and DBpedia Access Object
* */
@Service
class NamedEntityIdentifierService {

    public List<String> identifyEntities(String text) {

        String sentence = "Gottfried Wilhelm Leibniz was born in Leipzig in 1646 and attended the University of Leipzig from 1661-1666.";
        try {
            List<String> entities = NamedEntityParser.parseAllEntities(sentence);
            SparkQLQueryExecutor.ExecuteQueryForEntities(entities);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Collections.singletonList("test");
    }
}
