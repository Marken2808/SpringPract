import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import { useParams } from 'react-router';
import { Link } from "react-router-dom";
import { useDropzone } from 'react-dropzone';
import filterApi from '../../apis/filter';

const Profiles = () => {

    const { category } = useParams();
    console.log(category);

    const [profiles, setProfiles] = useState([]);


    useEffect(() => {
        const getProfiles = async () => {
            const response = await filterApi.getProfilesList(category, { params: {} });
            setProfiles(response);
        }
        getProfiles();
    }, [category]);

    
    return profiles.map((profile, index) => {
        return (
            <div key={index}>
                {profile.profileId ? (
                <Link
                    to={`/profile/${profile.profileId}`}
                >
                    <img className="image" src={`http://localhost:8080/api/v1/profile/${profile.profileId}/image/download`} />
                </Link>
                
                ) : null}
                <br />
                <br />
                <Dropzone {...profile} />
                <h1>{profile.profileName}</h1>
                <p>{profile.profileId}</p>

            </div>
        )
    })
}

function Dropzone({ profileId }) {
    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        console.log(file);
        const formData = new FormData();

        formData.append("file", file);
        axios.post(`http://localhost:8080/api/v1/profile/${profileId}/image/upload`,
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

    }, [])
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

function ProfileView() {
    return (
        <div className="Profile">
            <Profiles />
        </div>

    );
}

export default ProfileView;