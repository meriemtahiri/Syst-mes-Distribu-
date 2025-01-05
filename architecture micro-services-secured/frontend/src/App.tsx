import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/navbar';
import Products from './components/products';
import Customers from './components/customers';
import Commands from './components/commands';
import { ReactKeycloakProvider } from '@react-keycloak/web';
import keycloak from './keycloak';

function App() {
  return (
    <ReactKeycloakProvider authClient={keycloak}>
      <div style={{ backgroundColor: 'lightblue', minHeight: '100vh' }}>
        <Router>
          <Navbar />
          <Routes>
          <Route path="/products" element={<Products />} />
          <Route path="/customers" element={<Customers />} />
          <Route path="/commands" element={<Commands />} />
          </Routes>
        </Router>
      </div>
    </ReactKeycloakProvider>
  );
}

export default App;
