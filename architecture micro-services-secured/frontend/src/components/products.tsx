import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Divider, Alert, Empty } from 'antd';
import { EditOutlined, DeleteOutlined, EyeOutlined } from '@ant-design/icons';
import type { ColumnsType } from 'antd/es/table';
import { ProductData } from '../models/products';
import { useKeycloak } from '@react-keycloak/web';

// Définition des colonnes du tableau
const columns: ColumnsType<ProductData> = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
    render: (text: string) => <a>{text}</a>,
  },
  {
    title: 'Price',
    dataIndex: 'price',
    key: 'price',
  },
  {
    title: 'Action',
    key: 'action',
    render: (_, record) => (
      <span>
        <a onClick={() => console.log('Edit product', record.id)}>
          <EditOutlined style={{ color: 'green' }} />
        </a>
        <Divider type="vertical" />
        <a onClick={() => console.log('Delete product', record.id)}>
          <DeleteOutlined style={{ color: 'red' }} />
        </a>
        <Divider type="vertical" />
        <a onClick={() => console.log('View product', record.id)}>
          <EyeOutlined style={{ color: 'blue' }} />
        </a>
      </span>
    ),
  },
];

export default function Products() {
  const [data, setData] = useState<ProductData[]>([]);
  const [error, setError] = useState<string | null>(null);
  const { keycloak } = useKeycloak();

  useEffect(() => {
    if (!keycloak?.authenticated) return;  // Ne pas récupérer les produits si non authentifié

    // Récupération des données depuis l'API
    axios
      // .get(`http://localhost:8081/products?projection=fullProduct`)
      .get(`http://localhost:8081/api/products?projection=fullProduct`,{
        headers: {
          Authorization: `Bearer ${keycloak.token}`, // Token ajouté ici
        }},)
      .then((response) => {
        const products = response.data?.map((product: ProductData) => ({
          key: product.id,
          id: product.id,
          name: product.name,
          price: product.price,
        })) || [];
        setData(products);
        setError(null);
      })
      .catch((err) => {
        setError('Failed to fetch products. Please try again.');
        console.error('Error fetching data:', err);
      });
  }, [keycloak?.authenticated]);

  return (
    
    <div style={{
      alignItems: 'center',
      justifyContent: 'center',
      width: '100vw',
      padding: '20px',
      boxSizing: 'border-box'
    }}>
      <h1 style={{ marginTop: '40px', textAlign: 'center' }}>Products</h1>
      {error ? (
        <Alert message={error} type="error" showIcon />
      ) : data.length === 0 ? (
        <Empty description="No products found" />
      ) : (
        <Table
          columns={columns}
          dataSource={data}
          pagination={{ pageSize: 7, position: ['bottomCenter'] }}
        />
      )}
    </div>
  );
}
