import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Divider } from 'antd';
import { EditOutlined, DeleteOutlined, EyeOutlined } from '@ant-design/icons';
import type { ColumnsType } from 'antd/es/table';
import { ProductData } from '../models/products';

// Define table columns
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
    render: () => (
      <span>
        <a>
          <EditOutlined style={{ color: 'green' }} /> 
        </a>
        <Divider type="vertical" />
        <a>
          <DeleteOutlined style={{ color: 'red' }} /> 
        </a>
        <Divider type="vertical" />
        <a>
          <EyeOutlined style={{ color: 'blue' }} /> 
        </a>
      </span>
    ),
  },
];

export default function Products() {
  const [data, setData] = useState<ProductData[]>([]);

  useEffect(() => {
    // Fetch data from the API
    axios
      .get('http://localhost:8082/INVENTORY-SERVICE/products?projection=fullProduct')
      .then((response) => {
        const products = response.data._embedded?.products.map(
          (product: ProductData) => ({
            key: product.id, // Unique key for each item
            id: product.id,
            name: product.name,
            price: product.price,
          })
        ) || []; // Default to empty array if data is not available
        setData(products);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  }, []);

  return (
    <div style={{
      alignItems: 'center',
      justifyContent: 'center',
      width: '100vw',
      height: '100vh',
      padding: '20px',
      boxSizing: 'border-box'
    }}>
      <h1 style={{ marginBottom: '40px' , textAlign: 'center'}}>Products</h1>
      <Table columns={columns}
      dataSource={data}
      pagination={{ pageSize: 7, position: ['bottomCenter'] }} 
      />
    </div>
  );
}
