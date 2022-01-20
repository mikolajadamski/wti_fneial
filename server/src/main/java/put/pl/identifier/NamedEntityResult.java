package put.pl.identifier;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NamedEntityResult {

    @JsonProperty("head")
    NamedEntityResultHead head;

    @JsonProperty("results")
    NamedEntityResults results;
}
