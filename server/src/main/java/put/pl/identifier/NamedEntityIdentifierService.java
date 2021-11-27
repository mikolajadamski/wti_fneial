package put.pl.identifier;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

/**
* Bean containing parser and DBpedia Access Object
* */
@Service
class NamedEntityIdentifierService {

    public List<String> identifyEntities(String text) {

        // parse named entities
        List<String> entites = namedEntityParser.parseEntities(text);

        List<String> result = new ArrayList<>();
        // query to DBPedia
        entites.forEach(entity -> result.add(dbpediaDao.queryNamedEntity(entity)));

        return result;
    }

    private final NamedEntityParser namedEntityParser = new NamedEntityParser();

    private final DbpediaDao dbpediaDao = new DbpediaDao();
}
