import axios from 'axios';
import React, { useEffect, useState } from 'react'
import CartItem from './CartItem.jsx';
import "./CartItem.css"

const Cart = () => {

    const [data, setdata] = useState([]);
    const [err,seterr]=useState(null);
    const [loading,setloading]=useState(false);
    const id=localStorage.getItem('id');
    useEffect(() => {
      
        async function FetchCart() {
            try{
                setloading(true);
                const response=await axios.get(`http://localhost:8080/api/cart/${id}`);
                setdata(response.data);
                //console.log(response.data.cartDishes);
                setloading(false);
            }    
            catch(e){
                setloading(false);
                seterr(e);
            }        
        }
        FetchCart();
      
    }, [])
    let total=0;
    if(loading)return <h1>LOADING</h1>
    if(err)return <h1>ERROR</h1>



    async function handleRemove(dishId) {
        try{await axios.get(`http://localhost:8080/api/cart/delete/${id}/${dishId}`); }
        catch(e){
            console.log(e);
        }       
        const updatedCart=data.cartDishes.filter(dish=>dish.id!=dishId);
        setdata({...data,cartDishes: updatedCart});
    }

    if(!data.cartDishes || data.cartDishes.length==0)return <h2>No Dishes In Your Cart</h2>

    async function handleOrderNow() {
        try{
            const temp=[...data.cartDishes]
            const list=data.cartDishes;
            console.log(temp);
            const url=`http://localhost:8080/api/order/${id}`;
            const response=await axios.post(url,list,{
                params:{total:total}
            });
            console.log(response.data);
            setdata([]);
        }   
        catch(e){
            console.log(e);
        }     
    }

    return (
        <div>
            {data.cartDishes.map((element,index)=>{
                const details=[element.id,element.name,element.price,element.image,element.description,element.menu.restaurant.name];
                total+=details[2];
                return <CartItem key={`${element.id}-${index}`} details={details} handleRemove={handleRemove}/>
            })}
            <div className='Foot'>
                <div className='Content'>
                    <div className='total'><center> Total - {total}</center></div>
                    <button className='btnOrder' onClick={()=>handleOrderNow()}>OrderNow</button>
                </div>
            </div>
        </div>
    )
}

export default Cart