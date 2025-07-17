import React, { useState } from 'react'
import ArtificialIntelligence from './ArtificialIntelligence';
const DashboardData = () => {
    const [AIisOn, setAIisOn] = useState(false)
    let Name=localStorage.getItem('name');
    let Id=localStorage.getItem('id');
    let Email=localStorage.getItem('email');
    let Role=localStorage.getItem('role');
    let PhoneNo=localStorage.getItem('phone');
    if(AIisOn)return <ArtificialIntelligence AIisOn={AIisOn} setAIisOn={setAIisOn}/>
  return (
    <>
        <h1>Hello , {Name}</h1>
        <h4>Email - {Email}  PhoneNo.- {PhoneNo}</h4>
        <h3>Welcome to the APP</h3>
        <p>WE ARE HAPPY TO SEE YOU HERE </p>
        <p>From delicious pizzas to spicy biryanis, your next meal is just a few clicks away.
                    Use the navigation menu above to:</p>
        <ul>
                <li>Search For Restaurant</li>
            <li>Track Your Order</li>
            <li>See Order History</li>
        </ul>
        <h3>ENJOY !!!!</h3>
        <button onClick={()=>{setAIisOn(!AIisOn)}} className='AIEnter' style={{height:"60px",width:"180px",fontSize:"22px"}}>ENTER AI</button>
    </>
  )
}

export default DashboardData