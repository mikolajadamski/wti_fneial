package put.pl.identifier;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
* Bean containing parser and DBpedia Access Object
* */
@Service
class NamedEntityIdentifierService {

    public String identifyEntities(String sentence) {
        List<String> entities = NamedEntityParser.parseAllEntities(sentence);
        String outputAsJson = SparkQLQueryExecutor.ExecuteQueryForEntities(entities);
        return outputAsJson;
    }
}
