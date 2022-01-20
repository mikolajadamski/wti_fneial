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

    const getEntities = (event: any) => {
        event.preventDefault();
        fetch('api/identified-entity', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                text: sentence
            })
        }).then(response => response.json())
            .then(data => setResults(() => {
                return data.results.bindings.map((entityFromServer: any) => {
                    return {
                        entityUri: entityFromServer.DBEntity.value,
                        label: entityFromServer.label.value,
                        typeUri: entityFromServer.type.value
                    }
                })
            }))
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
            {results?.length !== 0 && results?.map((entity, id) => {
                return (
                    <div className="entity" key={id}>
                        <div>{'DBEntity'}</div>
                        <div><a href={entity.entityUri}>{entity.entityUri}</a></div>
                        <div>{'Label'}</div>
                        <div>{entity.label}</div>
                        <div>{'Type'}</div>
                        <div><a href={entity.typeUri}>{entity.typeUri}</a></div>
                    </div>
                );
            })
            }
        </div>
    );
}
