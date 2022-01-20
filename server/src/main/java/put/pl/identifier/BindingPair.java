package put.pl.identifier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BindingPair {

    @JsonProperty("type")
    String type;

    @JsonProperty("value")
    String value;
}
