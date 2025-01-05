import { CustomerData } from "./customer";
import { ProductData } from "./products";

export interface CommandData {
    id: number;
    status: string;
    customer: CustomerData;
    createdAt: string;
    total: number;
    productItems: [ProductItem]
  }

  export interface ProductItem {
    id: number,
    productId: number,
    quantity: number,
    discount: number,
    price: number,
    amount: number,
    product: ProductData,
  }