import React, { useState } from 'react'
import Container from './Container';
import axios from 'axios';
import { useNavigate ,Link} from 'react-router-dom';
import "./Container.css"
const Login = () => {

    const [userInput, setUserInput] = useState("");
    const [userPassword,setUserPassword]=useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`http://localhost:8080/api/user/login`, {
        "input":userInput,
        "password":userPassword,
      });
      console.log("Login Successful");
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('id',response.data.id);
      localStorage.setItem('email',response.data.email);
      localStorage.setItem('name',response.data.name);
      localStorage.setItem('role',response.data.role);
      localStorage.setItem('phone',response.data.phone);

      navigate('/dashboard');
    } 
    catch (error) {
        console.error("Login failed:", error.response?.data?.message || error.message);
        alert("Invalid username or password!");
    }
    finally{
        setUserInput("");
        setUserPassword("");
    }
  };
return (
    <div className='loginContainer'>
        <h2>LOGIN PAGE</h2>
        <form>
            <div><input type="text" name="login-input" id="login-input" placeholder="ENTER YOUR EMAIL OR PHONE NUMBER" value={userInput} required onChange={(e)=>setUserInput(e.target.value)} autoComplete='on'/></div>
            <div><input type="password" name="password" id="password" placeholder="ENTER PASSWORD" value={userPassword} required onChange={(e)=>setUserPassword(e.target.value)} autoComplete='current-password'/></div>
        </form>
        <button type='submit' onClick={handleLogin}>Login</button>
        <p>Don't have an account ? <Link to="/register">REGISTER HERE</Link></p>
    </div>
  );
}

export default Login