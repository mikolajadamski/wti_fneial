package put.pl.identifier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NamedEntityRequest {

    @JsonProperty("text")
    private String text;
}
