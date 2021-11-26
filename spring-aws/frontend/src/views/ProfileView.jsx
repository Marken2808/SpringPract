import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router';

import filterApi from '../apis/filter';
import PlayerCard from '../components/PlayerCard';
import { Link } from "react-router-dom";

const Profiles = () => {

    const { category } = useParams();

    const [profiles, setProfiles] = useState([]);

    useEffect(() => {
        const getProfiles = async () => {
            const response = await filterApi.getProfilesList(category, { params: {} });
            setProfiles(response);
        }
        getProfiles();
    }, [category]);


    return profiles.map((player, index) => {
        const link = '/' + category + '/' + player.profileId;
        
        return (
            <div key={index} className="App">

                <Link to={link} >
                    <PlayerCard player={player} category={category} key={index}/>
                </Link>

            </div>
        )
    })
}



function ProfileView() {
    return (
        <div className="Profile">
            <Profiles />
        </div>

    );
}

export default ProfileView;