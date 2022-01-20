package put.pl.identifier;

import org.springframework.stereotype.Service;
import put.pl.SparkQLQueryExecutor;

import java.util.Collections;
import java.util.List;

/**
* Bean containing parser and DBpedia Access Object
* */
@Service
class NamedEntityIdentifierService {

    public String identifyEntities(String text) {
        try {
            List<String> entities = NamedEntityParser.parseAllEntities(text);
            return SparkQLQueryExecutor.ExecuteQueryForEntities(entities);
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }
}
