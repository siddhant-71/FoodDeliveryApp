import React, { useState } from 'react'
import axios from 'axios';
import { useEffect } from 'react';
import History from './History'
import OrderBox from './OrderBox';
const OrderHistory = () => {

  const [err, seterr] = useState(null);
  const [data, setdata] = useState([]);
  const [loading, setloading] = useState(false);
  let id=localStorage.getItem('id');
  useEffect(() => {
    async function fetchData(){
      try{
        setloading(true);
        const response=await axios.get(`http://localhost:8080/api/order/user/${id}`);
        setdata(response.data);
        setloading(false);
      }
      catch(error){
        setloading(false);
        seterr(error);
      }
    }
    fetchData();
  }, [])
  if(loading){
    return <h4>LOADING</h4>
  }
  if(err){
    return <h3>ERROR PAGE</h3>
  }
  if(data.length===0){
    return (<div>
      <h2>ORDER HISTORY </h2>
      <h3>NO ORDERS</h3>
    </div>)
  }
  return (
    <div>
      <h2>Order History</h2>
      {data.map((order)=>
      {
        console.log(order);
        const orderInfo=[order.agentName,order.deliveryTime,order.id,order.orderStatus,order.orderTime,order.paymentMethod,order.restaurantName,order.totalAmount];
        return <OrderBox orderInfo={orderInfo} key={order.id} />
      })}
    </div>
  )
}

export default OrderHistory