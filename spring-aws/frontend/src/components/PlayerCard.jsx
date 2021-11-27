import React, { useCallback, useEffect, useState } from 'react';
import axios from 'axios';
import { useDropzone } from 'react-dropzone';
import { Link } from "react-router-dom";
import filterApi from '../apis/filter';

const PlayerCard = props => {


    const player = props.player;
    const category = props.category;

    const [url, setUrl] = useState([])

    const link = '/' + category + '/' + player.playerId;

    useEffect(() => {
        const getUrl = async () => {
            const response = await filterApi.download(category, player.playerId, { params: {} });
            setUrl(response);
        }
        getUrl();
    })

    return (
        <div>
            <Link to={link} >
                <img className="image"
                    src={url} alt="" />
            </Link>
            <br />
            <Dropzone player={player} />
            <h1>{player.playerName}</h1>
            {/* <p>{player.playerId}</p> */}
        </div>
    );

}

const Dropzone = props => {

    const player = props.player;

    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        console.log(file);
        const formData = new FormData();

        formData.append("file", file);
        axios.post(`http://localhost:8080/api/v1/players/${player.playerId}/image/upload`,
            formData,
            {
                headers: {
                    "Content-type": "multipart/form-data"
                }
            }
        ).then(() => {
            console.log("File was uploaded successfully")
        }).catch(err => {
            console.log(err)
        });

    }, [player])
    const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop })

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                isDragActive ?
                    <p>Drop the files here ...</p> :
                    <p>Upload new</p>
            }
        </div>
    )
}

export default PlayerCard;