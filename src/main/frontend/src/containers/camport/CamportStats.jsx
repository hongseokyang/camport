import React, { useEffect, useState } from 'react';
import CamportContent from '../../components/camport/CamportContent';
import axios from 'axios';
import { withRouter } from 'react-router-dom';

const CamportStats = ({ history }) => {
    const [camports, setCamports] = useState("");

    useEffect(() => {
        axios.get("/api/camport", {
            params: {
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
            .then(function () {
                console.log("axios");
            });
    });

    return (
        <CamportContent camports={camports} />
    )
}
export default withRouter(CamportStats);