import { createRoot } from 'react-dom/client'
import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'antd/dist/reset.css'
import Navbar from './components/navbar'
//import Customers from './components/customers.tsx'
//import Products from './components/products.tsx'
//import Commands from './components/commands.tsx'

createRoot(document.getElementById('root')!).render(
  <div style={{backgroundColor: 'lightblue'}}>
    <Navbar/>
  </div>
)
