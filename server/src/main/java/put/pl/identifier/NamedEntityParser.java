package put.pl.identifier;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
class NamedEntityParser {

    List<String> parseEntities(String text) {
        // TODO entity parsing algorithm (maybe some ready solutions)
        return Arrays.stream(text.split("\\s+")).collect(Collectors.toList());
    }
}
