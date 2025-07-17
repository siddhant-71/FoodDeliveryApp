import React, { useEffect, useState } from 'react'
import "./MenuTable.css";
import Row from "./Row.jsx"
import axios from 'axios';
const MenuTable = ({data ,Id}) => {
    
  return (
    <div className="Menu">
                <table>
                    <thead>
                        <tr>
                            <th className='One'>Dish</th>
                            <th className='Two'>Price</th>
                            <th className='Three'>Desc</th>
                            <th className='Four'>AddToCart</th>
                        </tr>
                    </thead>
                    <tbody>
                        {data.map((entry)=>{
                            let detail=[entry.name,entry.price,entry.description,entry.id];
                            return <Row detail={detail} Id={Id} key={entry.id}/>
                        })}
                    </tbody>
                </table>
            </div>
  )
}

export default MenuTable