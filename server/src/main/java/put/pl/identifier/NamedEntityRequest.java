package put.pl.identifier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NamedEntityRequest {

    @JsonProperty("text")
    private String text;
}
