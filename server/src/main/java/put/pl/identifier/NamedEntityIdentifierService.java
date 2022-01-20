package put.pl.identifier;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import put.pl.SparkQLQueryExecutor;

import java.util.Collections;
import java.util.List;

/**
* Bean containing parser and DBpedia Access Object
* */
@Service
class NamedEntityIdentifierService {

    ObjectMapper objectMapper = new ObjectMapper();

    public NamedEntityResult identifyEntities(String text) {
        try {
            List<String> entities = NamedEntityParser.parseAllEntities(text);
            String result =  SparkQLQueryExecutor.ExecuteQueryForEntities(entities);
            NamedEntityResult namedEntityResult = objectMapper.readValue(result, NamedEntityResult.class);
            System.out.println(namedEntityResult);
            for (ParsedEntity parsedEntity :namedEntityResult.results.bindings) {
                System.out.println(parsedEntity.dbEntity.value);
                System.out.println(parsedEntity.type.value);
                System.out.println(parsedEntity.label.value);
                System.out.println();
            }
            return namedEntityResult;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
