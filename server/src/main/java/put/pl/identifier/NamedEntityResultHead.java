package put.pl.identifier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NamedEntityResultHead {

    @JsonProperty("vars")
    List<String> vars;
}
