import React from 'react';
import CamportStats from '../containers/camport/CamportStats';
import CamportContent from '../components/camport/CamportContent';

function CamportStatsPage() {
    return (
        // <CamportProvider.Provider value={camports}>
        //     <CamportContent />
        // </CamportProvider.Provider>
        <CamportContent>
            <div>캠핑조회</div>;
            <CamportStats />
        </CamportContent>
    )
}

export default CamportStatsPage;