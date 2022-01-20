import {useState} from "react";
import './named-entity-parser.css';

interface Entity {
    entityUri: string,
    label: string,
    typeUri: string
}

export const NamedEntityParser = () => {

    const [sentence, setSentence] = useState<string>('');
    const [results, setResults] = useState<Entity[]>();
    const [isFetching, setIsFetching] = useState<boolean>(false);

    const getEntities = (event: any) => {
        event.preventDefault();
        setIsFetching(() => true);
        fetch('api/identified-entity', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                text: sentence
            })
        }).then(response => response.json())
            .then(data => setResults(() => {
                if (!data) {
                    return null;
                }
                return data.results.bindings.map((entityFromServer: any) => {
                    return {
                        entityUri: entityFromServer.DBEntity.value,
                        label: entityFromServer.label.value,
                        typeUri: entityFromServer.type.value
                    }
                })
            }))
            .catch(() => setResults(() => []))
            .finally(() => setIsFetching(() => false));
    }

    return (
        <div className="wrapper">
            <form  style={{"marginBottom": "20px"}} onSubmit={getEntities}>
                <div className="form-group">
                    <label htmlFor="sentence">Sentence</label>
                    <input type="text"
                           className="form-control"
                           id="sentence"
                           placeholder="Enter sentence"
                           onChange={event => setSentence(() => event.target.value)}/>
                </div>
                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
            {results?.length !== 0 && !isFetching && results?.map((entity, id) => {
                return (
                    <div className="entity" key={id}>
                        <div className="data-row">
                            <div className="data-label">{'DBEntity'}</div>
                            <div><a href={entity.entityUri} target="_blank">{entity.entityUri}</a></div>
                        </div>
                        <div className="data-row">
                            <div className="data-label">{'Label'}</div>
                            <div>{entity.label}</div>
                        </div>
                       <div className="data-row">
                           <div className="data-label">{'Type'}</div>
                           <div><a href={entity.typeUri} target="_blank">{entity.typeUri}</a></div>
                       </div>
                    </div>
                );
            })
            }
            {isFetching &&
                <h2>
                    {'Fetching...'}
                </h2>
            }
            {results?.length === 0 && !isFetching &&
            <h2>
                {'No data'}
            </h2>}
        </div>
    );
}
