import { useContext } from 'react';
import CamportProvider from './CamportProvider';

export default function CamportContent(props) {
    //const camports = useContext(CamportProvider);
    const camports = props.camports;

    return (        
        <ul>
            {camports.map((camport) => (
                <li key={camport.camportId}>{camport.campLocationName}</li>
            ))}
        </ul>
    );
}