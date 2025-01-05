import React from 'react';
import { createRoot } from 'react-dom/client';
import App from './App';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'antd/dist/reset.css';

createRoot(document.getElementById('root')!).render(
    <App />
);
