package put.pl.identifier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParsedEntity {

    @JsonProperty("DBEntity")
    BindingPair dbEntity;

    @JsonProperty("type")
    BindingPair type;

    @JsonProperty("label")
    Label label;

}
