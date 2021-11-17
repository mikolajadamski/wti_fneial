import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;


public class Main {

    private static final String INPUT_FILE_NAME = "training/oke17task1Training.xml.ttl";

    public static void main(String[] args) {
        Model model = ModelFactory.createDefaultModel();
        model.read(INPUT_FILE_NAME);
        model.write(System.out);
    }
}
