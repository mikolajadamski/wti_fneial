package put.pl.identifier;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


/**
 * Endpoint responsible for receiving text
 * */
@RequestMapping("api/")
@RestController
@RequiredArgsConstructor
class NamedEntityIdentifierRestController {

    @PostMapping("identified-entity")
    public NamedEntityResult getDbpediaResponse(@RequestBody NamedEntityRequest request) {
        return namedEntityIdentifierService.identifyEntities(request.getText());
    }

    private final NamedEntityIdentifierService namedEntityIdentifierService;
}
