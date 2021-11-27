package put.pl.identifier;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NamedEntityParserTest {

    @Test
    void shouldParseEntity() {
        // given
        String textToParse = "Dummy test for dummy parser";

        // when
        List<String> result = subject.parseEntities(textToParse);

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(5);
    }

    private final NamedEntityParser subject = new NamedEntityParser();
}
