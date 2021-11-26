import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';

import filterApi from '../apis/filter';
import PlayerCard from '../components/PlayerCard';

const DetailView = () => {

    const { category, id } = useParams();

    const [player, setPlayer] = useState(false);    // to catch init empty

    useEffect(() => {
        const getDetail = async () => {
            const response = await filterApi.detail(category, id, { params: {} });
            setPlayer(response);
        }
        getDetail();
    }, [category, id]);

    return (
        <div className="App">
            {
                player 
                ? (<PlayerCard player={player} category={category} />)
                : null
            }
            
        </div>
    );
}

export default DetailView;