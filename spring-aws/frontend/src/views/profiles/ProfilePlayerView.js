import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import { useLocation } from "react-router-dom";
import { useParams } from 'react-router';
import { useDropzone } from 'react-dropzone';

const ProfilePlayerView = () => {

    const { category, id } = useParams();

    console.log(category);

    const [player, setPlayer] = useState(null);

    

    useEffect(() => {
        const getPlayer = async () => {
            await axios.get('http://localhost:8080/api/v1/profile/' + id)
                .then(res => {
                    console.log(res);
                    setPlayer(res.data)
            });
        }
        getPlayer();
    }, [id]);

    return (
        <div>
            <h1>
            alo {id}
            </h1>
        </div>
    );
  
}

// function ProfilePlayerView() {
    
//     return(<ProfilePlayer/>);
// }

// function Dropzone({ profileId }) {
//     const onDrop = useCallback(acceptedFiles => {
//         const file = acceptedFiles[0];
//         console.log(file);
//         const formData = new FormData();

//         formData.append("file", file);
//         axios.post(`http://localhost:8080/api/v1/profile/${profileId}/image/upload`,
//             formData,
//             {
//                 headers: {
//                     "Content-type": "multipart/form-data"
//                 }
//             }
//         ).then(() => {
//             console.log("File was uploaded successfully")
//         }).catch(err => {
//             console.log(err)
//         });

//     }, [])
//     const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop })

//     return (
//         <div {...getRootProps()}>
//             <input {...getInputProps()} />
//             {
//                 isDragActive ?
//                     <p>Drop the files here ...</p> :
//                     <p>Upload new</p>
//             }
//         </div>
//     )
// }

export default ProfilePlayerView;