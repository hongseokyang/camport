import React, {useEffect, useState} from 'react';
import CamportContent from '../../components/CamportContent';
import axios from 'axios';

export default function CamportStats() {

    const [camports, setCamports] = useState("");    

    useEffect(() => {
        axios.get("/a/camport", {
            params:{
                page: 0,
                size: 10
            }
        })
        .then((response) => {
            console.log(response);
            setCamports(response.data.content);
        })
        .catch(function (error) {
            console.log(error);
        })
        .then(function() {
            console.log("axios");
        });
    });
    
    return (
        <CamportContent camports={camports} />
    )
}