import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";
import { useDropzone } from 'react-dropzone';

const Profiles = () => {

    const [Profiles, setProfiles] = useState([]);

    const fetchProfiles = () => {
        axios.get("http://localhost:8080/api/v1/profile").then(res => {
            console.log(res);
            setProfiles(res.data)
        });
    }

    useEffect(() => {
        fetchProfiles();
    }, []);

    
    return Profiles.map((profile, index) => {
        return (
            <div key={index}>
                {profile.profileId ? (
                // <Link
                //     to={`./${profile.profileId}`}
                //     props={`${profile.profileId}`}
                // >
                // <Link
                //     to={{pathName: `./${profile.profileId}`,
                //         state: {
                //             fromNotifications: true
                //         }}}
                // >
                    <img className="image" src={`http://localhost:8080/api/v1/profile/${profile.profileId}/image/download`} />
                // </Link>
                
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