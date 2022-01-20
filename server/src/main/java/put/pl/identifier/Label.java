package put.pl.identifier;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Label {

    @JsonProperty("type")
    String type;

    @JsonProperty("value")
    String value;

    @JsonProperty("xml:lang")
    String lang;
}
