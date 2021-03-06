package put.pl;

import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class SparkQLQueryExecutor {

    public static void ExecuteQueryForEntities(List<String> entities) throws Exception{
        ExecuteSparkQLQuery(generateQueryForEntities(entities));
    }

    public static String generateQueryForEntities(List<String> entities) throws Exception{
        String prefixes = getFileContent("SparkQLQueryTemplates/prefixes.txt");
        String singleItemStatementTemplate = getFileContent("SparkQLQueryTemplates/single-item-query-statement.txt");
        String sparkQLQuery = prefixes;
        List<String> generatedItemsQueries = new ArrayList<String>();
        for(String itemToQuery: entities){
            String generatedQueryForItem = singleItemStatementTemplate.replace("{{ENTITY}}", itemToQuery);
            generatedItemsQueries.add(generatedQueryForItem);
        }
        sparkQLQuery += "SELECT *  WHERE{";
        sparkQLQuery += String.join(" UNION ", generatedItemsQueries);
        sparkQLQuery += "}"; 
        return sparkQLQuery;
    }

    public static void ExecuteSparkQLQuery(String queryStr) throws Exception{
        Query query = QueryFactory.create(queryStr);
        
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", queryStr) ) {
            ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
            ResultSet rs = qexec.execSelect();
            ResultSetFormatter.out(System.out, rs, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getFileContent(String filePath) throws Exception{
        Charset encoding = StandardCharsets.US_ASCII;
        byte[] encoded = null;
        encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded, encoding);
    }
}
