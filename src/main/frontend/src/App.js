import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import CamportStatsPage from './pages/CamportStatsPage';

function App() {
  return (    
    <BrowserRouter>
      <Route path="/api/camport" component={CamportStatsPage} />
    </BrowserRouter>
  );
}

export default App;
