import React from 'react'
import "./RestaurantViewContainer.css";
import axios from 'axios';
import { useState,useEffect } from 'react';
import MenuTable from './MenuTable';
const RestaurantViewContainer = ({resInfo}) => {
    const [IsMenu, setIsMenu] = useState(false);
    const Area=resInfo[0];
    const City=resInfo[1];
    const Desc=resInfo[2];
    const Img=resInfo[3];
    const Menu=resInfo[4];
    const Name=resInfo[5];
    const Pincode=resInfo[6];
    const Rating=resInfo[7];
    const RatingCnt=resInfo[8];
    const State=resInfo[9];
    const Id=resInfo[10];

    const [data, setdata] = useState([]);
    const [loading,setloading]=useState(false);
    const [err,seterr]=useState(null);

    useEffect(() => {
        async function FetchMenu() {
            try{
                setloading(true);
                const response=await axios.get(`http://localhost:8080/api/menu/restaurantId/${Id}`);
                setdata(response.data);
                //console.log(response.data);
                setloading(false);
            }
            catch(e){
                setloading(false);
                seterr(e);
            }        
        }
        FetchMenu();
    }, [])
  return (
    <div className='mainBox'>
        <div className='upper'>
            <div className='one'>
                <div><h2>{Name}</h2></div>
                <div>{Area},{City},{Pincode},{State}</div>
            </div>
            <div className='two'>{Img}</div>
            <div className='three'>{Rating} ({RatingCnt})</div>
        </div>
        <div className='lower'>
            <div className='desc'>
                Description - {Desc} 
            </div>
            <div>
                <button onClick={()=>setIsMenu(!IsMenu)}>ViewMenu</button>
            </div>
        </div>
        {IsMenu && (<MenuTable data={data} Id={Id} />)}
    </div>
  )
}

export default RestaurantViewContainer