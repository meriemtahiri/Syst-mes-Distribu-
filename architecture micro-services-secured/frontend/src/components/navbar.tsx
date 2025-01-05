import React, { useState } from 'react'; 
import { Menu, Button } from 'antd';
import { TeamOutlined, AppstoreOutlined, MacCommandOutlined } from '@ant-design/icons';
import { useNavigate } from 'react-router-dom';
import { useKeycloak } from '@react-keycloak/web';

const Navbar = () => {
  const [current, setCurrent] = useState('customers');
  const navigate = useNavigate();
  const { keycloak } = useKeycloak();

  // Fonction pour vérifier si l'utilisateur possède un rôle spécifique
  const hasRole = (role: string) => {
    return keycloak?.tokenParsed?.realm_access?.roles?.includes(role);
  };

  const handleClick = (e) => {
    setCurrent(e.key);
    navigate(`/${e.key}`);
  };

  console.log("Token:", keycloak?.token);

  return (
    <>
      <div
        style={{
          position: 'fixed',
          top: 0,
          width: '100%',
          zIndex: 1000,
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          padding: '0 20px',
          backgroundColor: '#fff',
        }}
      >
        {/* Menu de navigation */}
        <Menu
          onClick={handleClick}
          selectedKeys={[current]}
          mode="horizontal"
          style={{ flex: 1 }}
        >
          <Menu.Item key="customers">
            <TeamOutlined style={{ color: '#1DA57A', marginRight: '10px' }} />
            Customers
          </Menu.Item>

          {/* Affiche "Products" si l'utilisateur est authentifié et a le rôle "ADMIN" */}
          {keycloak?.authenticated && hasRole('ADMIN') && (
            <Menu.Item key="products">
              <AppstoreOutlined style={{ color: '#8A2BE2', marginRight: '10px' }} />
              Products
            </Menu.Item>
          )}

          <Menu.Item key="commands">
            <MacCommandOutlined style={{ color: '#1890FF', marginRight: '10px' }} />
            Commands
          </Menu.Item>
        </Menu>

        {/* Boutons Login / Logout */}
        <div style={{ marginLeft: 'auto' }}>
          {!keycloak?.authenticated ? (
            <Button
              type="primary"
              onClick={() => keycloak.login()}
            >
              Login
            </Button>
          ) : (
            <Button
              type="primary"
              danger
              onClick={() => keycloak.logout()}
            >
              Logout
            </Button>
          )}
        </div>
      </div>
    </>
  );
};

export default Navbar;
