package put.pl.identifier;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NamedEntityResults {

    @JsonProperty("bindings")
    List<ParsedEntity> bindings;
}
