import React, { useState } from 'react';
import { Menu } from 'antd';
import { TeamOutlined, AppstoreOutlined, MacCommandOutlined } from '@ant-design/icons';
import Customers from './customers';
import Products from './products';
import Commands from './commands';


const Navbar = () => {
  const [current, setCurrent] = useState('customers');

  const handleClick = e => {
    setCurrent(e.key);
  };

  const renderContent = () => {
    switch (current) {
      case 'customers':
        return <Customers />;
      case 'products':
        return <Products />;
      case 'commands':
        return <Commands />;
      default:
        return null;
    }
  };

  return (
    <>
      <div style={{ position: 'fixed', top: 0, width: '100%', zIndex: 1000 }}>
        <Menu onClick={handleClick} selectedKeys={[current]} mode="horizontal">
          <Menu.Item key="customers">
            <TeamOutlined style={{ color: '#1DA57A', marginRight: '10px' }} />
            Customers
          </Menu.Item>
          <Menu.Item key="products">
            <AppstoreOutlined style={{ color: '#8A2BE2', marginRight: '10px' }} />
            Products
          </Menu.Item>
          <Menu.Item key="commands">
            <MacCommandOutlined style={{ color: '#1890FF', marginRight: '10px' }} />
            Commands
          </Menu.Item>
        </Menu>
      </div>
      
      {/* Render the selected component below the navbar */}
      <div >
        {renderContent()}
      </div>
    </>
  );
};

export default Navbar;
