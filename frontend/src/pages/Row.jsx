import axios from 'axios';
import React, { useState } from 'react'

const Row = ({detail,Id}) => {
    const Name=detail[0];
    const Price=detail[1];
    const Desc=detail[2];
    const Dishid=detail[3];
    const id=localStorage.getItem('id');
    const [err, seterr] = useState(null)
    //api/cart/id/dishId
    async function addToCart(){
        try{
            const url=`http://localhost:8080/api/cart/${id}/${Dishid}`;
            const response = await axios.get(url);
            console.log(response.data);
        }
        catch(e){
            seterr(e);
        }
    }
  return (
    <tr>
        <td className='One'>{Name}</td>
        <td className='Two'>Rs. {Price}/-</td>
        <td className='Three'>{Desc}</td>
        <td className='Four'><button className='btn' onClick={addToCart}>add</button></td>
    </tr>
  )
}

export default Row