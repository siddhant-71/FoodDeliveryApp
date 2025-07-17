import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import "./Container.css";
import axios from 'axios';
const Register = () => {

  const navigate=useNavigate();
  const [loading, setloading] = useState(false);
  const [err,seterr]=useState(null);
  const [name, setname] = useState("");
  const [lastName, setlastName] = useState("");
  const [email,setEmail]=useState("");
  const [phone,setPhone]=useState("");
  const [date,setdate]=useState("");
  const [password,setpassword]=useState("");

  async function signUP() {
    
    const url=`http://localhost:8080/api/user/addUser`;
    const data={
          name:`${name}`,
          lastName:`${lastName}`,
          birthDate:`${date}`,
          email:`${email}`,
          password:`${password}`,
          phone:`${phone}`,
          role:"CUSTOMER"
        }
    try{
      setloading(true);
      console.log(data);
      await axios.post(url,data);
      setloading(false);
      alert("signup successfull , Login Now !")
      navigate('/')
    }
    catch(e){
      setloading(false);
      console.log(e);
      seterr(e);
    }
  }
  if(loading)return <h1>Loading</h1>
  if(err)return <h1>Error</h1> 


  return (
    <div className='RegisterContainer'>
      <h2>SIGNUP PAGE</h2>
        <form>
            <div><input type="text" name="login-input"  placeholder="Enter Your Name" value={name} required onChange={(e)=>setname(e.target.value)} autoComplete='on'/></div>
            <div><input type="text" name="password" placeholder="Enter Last Name" value={lastName} required onChange={(e)=>setlastName(e.target.value)} autoComplete='on'/></div>
            <div><input type="text" name='email' placeholder='Enter Email' value={email} required onChange={(e)=>setEmail(e.target.value)} autoComplete='on'/></div>
            <div><input type="number" name='phone' placeholder='Enter Phoneno' value={phone} required onChange={(e)=>setPhone(e.target.value)} autoComplete='on'/></div>
            <div><input type="date" name='dob' placeholder='Enter DOB' value={date} required onChange={(e)=>setdate(e.target.value)} autoComplete='on'/></div>
            <div><input type="password" name='pass' placeholder='Enter Password' value={password} required onChange={(e)=>setpassword(e.target.value)} autoComplete='on'/></div>
        </form>
        <button type='submit' onClick={signUP}>SIGN UP</button>
        <p>Already Have an account ? <Link to="/">Login Here</Link></p>
    </div>
  )
}

export default Register