import React from 'react'
import "./OrderBox.css"
const OrderBox = ({orderInfo}) => {
    const OrderTime=orderInfo[4];
    const OrderStatus=orderInfo[3];
    const DeliveryTime=orderInfo[1];
    const RestaurantName=orderInfo[6];
    const totalAmount=orderInfo[7];
    const paymentMode=orderInfo[5];
    const Agent=orderInfo[0];
  return (
    <div className='mainBox'>
        <div className='first'>
            <p>OrderTime - {OrderTime}</p>
            <p>OrderStatus - {OrderStatus}</p>
            <p>Delivery Time - {DeliveryTime}</p>
        </div>
        <div className='second'>
            <p>Restaurant - {RestaurantName}</p>
            <button>Review</button>
        </div>
        <div className='third'>
            <p>Total Amount - {totalAmount}</p>
            <p>Payment Mode-{paymentMode}</p>
            <p>Agent Name -{Agent}</p>
        </div>
    </div>
  )
}

export default OrderBox