import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Divider } from 'antd';
import { EditOutlined, DeleteOutlined, EyeOutlined } from '@ant-design/icons';
import type { ColumnsType } from 'antd/es/table';
import { CustomerData } from '../models/customer';


// Define table columns
const columns: ColumnsType<CustomerData> = [
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
    title: 'Email',
    dataIndex: 'email',
    key: 'email',
  },
  {
    title: 'Action',
    key: 'action',
    render: () => (
      <span>
        <a>
          <EditOutlined style={{ color: 'green' }} /> {/* Icône Edit */}
        </a>
        <Divider type="vertical" />
        <a>
          <DeleteOutlined style={{ color: 'red' }} /> {/* Icône Delete */}
        </a>
        <Divider type="vertical" />
        <a>
          <EyeOutlined style={{ color: 'blue' }} /> {/* Icône View */}
        </a>
      </span>
    ),
  },
];

export default function Customers() {
  const [data, setData] = useState<CustomerData[]>([]);

  useEffect(() => {
    // Fetch data from the API
    axios
      .get('http://localhost:8082/CUSTOMER-SERVICE/customers?projection=fullCustomer')
      .then((response) => {
        const customers = response.data._embedded?.customers.map(
          (customer: CustomerData) => ({
            key: customer.id, // Unique key for each item
            id: customer.id,
            name: customer.name,
            email: customer.email,
          })
        ) || []; // Default to empty array if data is not available
        setData(customers);
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
      <h1 style={{ marginBottom: '40px' , textAlign: 'center'}}>Customers</h1>
      <Table columns={columns}
      dataSource={data}
      pagination={{ pageSize: 7, position: ['bottomCenter'] }} // Display 5 customers per page
      />
    </div>
  );
}
