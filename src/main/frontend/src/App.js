import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import CamportStatsPage from './pages/CamportStatsPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';

function App() {
  return (
    <BrowserRouter>
      <Route path="/api/camport" component={CamportStatsPage} />
      <Route path="/login" component={LoginPage} />
      <Route path="/register" component={RegisterPage} />
      <Route path="/camport" component={CamportStatsPage} />
    </BrowserRouter>
  );
}

export default App;
