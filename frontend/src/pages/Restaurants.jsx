import React from 'react'
import "./Body.css"
import axios from 'axios';
import { useState,useEffect } from 'react';
import RestaurantViewContainer from './RestaurantViewContainer';
const Restaurants = () => {

  const [data, setdata] = useState([]);
  const [loading, setloading] = useState(false);
  const [err, seterr] = useState(null);
  const id=localStorage.getItem('id');
  useEffect(() => {
    async function FetchAllRestaurants() {
      // let cityRes=async()=>await axios.get(`http://localhost:8080/api/user/cityOfUser/${id}`)
      // console.log(cityRes.data);
      // const city=cityRes.data;
      // if(city == ""){
      //   city=axios.get(`http://localhost:8080/api/user/stateOfUser/${id}`);
      // }
      try{
        //const response= axios.get(`http://localhost:8080/api/restaurant/city/${city}`);
        const response=await axios.get(`http://localhost:8080/api/restaurant/all`)
        setdata(response.data);
        setloading(false);
      } 
      catch(e){
        setloading(false);
        seterr(e);
      }    
    }
    FetchAllRestaurants();
  }, [])
  if(loading){
    return <p>Loading</p>
  }
  if(err){
    return <h2>ERROR</h2>
  }
  return (
    <div>
      <center><h1>Restaurants</h1></center>
        {data.map((res)=>{
          const resInfo=[res.area,res.city,res.description,res.image,res.menu,res.name,res.pincode,res.rating,res.ratingCount,res.state,res.id];
          return <RestaurantViewContainer resInfo={resInfo} key={res.id}/>
        })}
    </div>
  )
}

export default Restaurants