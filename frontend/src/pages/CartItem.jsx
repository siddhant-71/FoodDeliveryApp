import React, { useEffect } from 'react'
import "./CartItem.css"
const CartItem = ({details,handleRemove}) => {  
    const id=details[0];
    const name=details[1];
    const price=details[2];
    const image=details[3];
    const desc=details[4];
    const restName=details[5];
  return (
    <div className='dish'>
        <div className='Dimg'>{image}</div>
        <div className='Ddet'>
            <div className='Name'>{restName}</div>
            <div className='DishName'>{name}</div>
            <div className='Description'><p>{desc}</p></div>
            <div className='Price'>{price}  /-</div>
        </div>
        <div className='Delete'><button className='DeleteBtn' onClick={()=>handleRemove(id)}>Remove</button></div>
    </div>
  )
}

export default CartItem