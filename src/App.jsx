import React from 'react';
import Header from './components/Header';
import Content from './components/Content';
import Footer from './components/Footer';
import { Container } from '@mui/material';
import Visualizar from './components/Visualizar';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import AppProvider from './providers/appProvider';
import Cadastrar from './components/Cadastrar';

const App = () => {
  return (
    <BrowserRouter>
      <AppProvider>
        <div className="bodyContainer">
          <div className="headerContainer">
            <Header />
          </div>
          <div className="contentContainer">
            <Routes>
              <Route path="/" element={<Content />} />
              <Route path="visualizar/:id/" element={<Visualizar />} />
              <Route path="cadastrar" element={<Cadastrar />} />
            </Routes>
          </div>
          <div className="footerContainer">
            <Container>
              <Footer />
            </Container>
          </div>
        </div>
      </AppProvider>
    </BrowserRouter>
  );
};

export default App;
