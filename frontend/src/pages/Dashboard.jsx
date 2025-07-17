import React from 'react'
import Container from './Container';
import { Link, Outlet, useNavigate } from 'react-router-dom';
import ArtificialIntelligence from './ArtificialIntelligence';

const Dashboard = () => {
    let Name=localStorage.getItem('name');
    let Id=localStorage.getItem('id');
    let Email=localStorage.getItem('email');
    let Role=localStorage.getItem('role');
    let PhoneNo=localStorage.getItem('phone');
    const navigate=useNavigate();
    const handleLogout=()=>{
        localStorage.clear();
        navigate('/');
    }

  return (
    <Container>
        <div className='headerClass'>
            <h4>({Role})</h4>
            <h4>HELLO {Name}</h4>
            <button onClick={handleLogout}>LOGOUT</button>
        </div>
        <nav>
            <ul>
                <li><Link to="/dashboard">HOME</Link></li>
                <li><Link to="/dashboard/restaurant">RESTAURANT</Link></li>
                <li><Link to="/dashboard/track">TRACK ORDER</Link></li>
                <li><Link to="/dashboard/history">ORDER HISTORY</Link></li>
                <li><Link to="/dashboard/cart">CART</Link></li>
            </ul>
        </nav>
        <Outlet/>
    </Container>
  )
}

export default Dashboard