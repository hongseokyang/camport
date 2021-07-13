import React from 'react';
import CamportStats from '../components/camportStats/CamportStats';
import CamportProvider from '../components/CamportProvider';

function CamportStatsPage() {
    return (
        // <CamportProvider.Provider value={camports}>
        //     <CamportContent />
        // </CamportProvider.Provider>
        <CamportStats />
    )
}

export default CamportStatsPage;