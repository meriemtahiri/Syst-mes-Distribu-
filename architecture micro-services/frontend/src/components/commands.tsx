import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Divider, Tag, Button, Menu, Dropdown } from 'antd';
import { EditOutlined, DeleteOutlined, EyeOutlined, DownOutlined } from '@ant-design/icons';
import type { ColumnsType } from 'antd/es/table';
import { CommandData } from '../models/command';
import { ProductData } from '../models/products';

// Define table columns
const columns: ColumnsType<CommandData> = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'Status',
    dataIndex: 'status',
    key: 'status',
    render: (status: string) => {
      let color;
      switch (status) {
        case 'COMPLETED':
          color = 'green';
          break;
        case 'PENDING':
          color = 'orange';
          break;
        case 'CANCELLED':
          color = 'red';
          break;
      }
      return <Tag color={color}>{status}</Tag>;
    }
  },
  {
    title: 'Customer',
    dataIndex: 'customer',
    key: 'customer',
    render: (customer) => customer?.name, // Display customer's name
  },
  {
    title: 'CreatedAt',
    dataIndex: 'createdAt',
    key: 'createdAt',
  },
  {
    title: 'Total',
    dataIndex: 'total',
    key: 'total',
  },
  {
    title: 'Products',
    key: 'products',
    dataIndex: 'products',
    render: (productItems = []) => {
      const colors = ['magenta', 'red', 'volcano', 'orange', 'gold', 'lime', 'green', 'cyan', 'blue', 'geekblue', 'purple'];

      // Create menu items for each product
      const productMenu = (
        <Menu style={{ maxHeight: '200px', overflowY: 'auto' }}>
          {productItems.map((item: { product: ProductData }) => {
            const randomColor = colors[Math.floor(Math.random() * colors.length)];
            return (
              <Menu.Item key={item.product.id}>
                <Tag color={randomColor} key={item.product.id}>
                  {item.product.name}
                </Tag>
              </Menu.Item>
            );
          })}
        </Menu>
      );

      return (
        <Dropdown overlay={productMenu} trigger={['click']}>
          <Button>
            Afficher les produits <DownOutlined />
          </Button>
        </Dropdown>
      );
    },
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

export default function Commands() {
  const [data, setData] = useState<CommandData[]>([]);

  useEffect(() => {
    // Fetch data from the API
    axios
      .get('http://localhost:8082/BILLING-SERVICE/api/bills')
      .then((response) => {
        const commands = response.data.map(
          (command: CommandData) => ({
            key: command.id,
            id: command.id,
            status: command.status,
            customer: command.customer, // Include customer details
            createdAt: command.createdAt,
            total: command.total,
            products: command.productItems, // Map productItems to products
          })
        ) || [];
        setData(commands);
        console.log(commands);
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
      <h1 style={{ marginBottom: '40px', textAlign: 'center' }}>Commands</h1>
      <Table 
        columns={columns}
        dataSource={data}
        pagination={{ pageSize: 7, position: ['bottomCenter'] }}
      />
    </div>
  );
}
