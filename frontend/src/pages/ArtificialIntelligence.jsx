import React, { useEffect ,useState} from 'react'
import "./ArtificialIntelligence.css"
import axios from 'axios'
import RecipeComponent from './Reciepe'

const ArtificialIntelligence = ({AIisOn , setAIisOn}) => {
    const [one, setone] = useState("")
    const [two, settwo] = useState("")
    const [three, setthree] = useState("")
    const [four, setfour] = useState("")
    const [five, setfive] = useState("")
    const [six, setsix] = useState("")
    const [seven, setseven] = useState("")
    const [eight, seteight] = useState("")
    const [nine, setnine] = useState("")
    const [ten, setten] = useState("")
    const [response, setresponse] = useState("");

    const [loading, setloading] = useState(false);
    const [err, seterr] = useState(null)
    const [data, setdata] = useState("");
    async function generateReceipe() {
        const question=`${one},${two},${three},${four},${five},${six},${seven},${eight},${nine},${ten} `;
        try{
            setloading(true);
            const url=`http://localhost:8080/api/ai/ask`;
            const gotResponse=await axios.post(url,question);
            console.log(gotResponse.data.candidates[0].content.parts[0].text);
            setresponse(gotResponse.data.candidates[0].content.parts[0].text);
            setloading(false);
        }
        catch(e){
            console.log(e);
        }
    }
  return (
    <>
        <button style={{width:"20%",height:"50px",fontSize:"25px"}} onClick={()=>{setAIisOn(!AIisOn)}}>CLOSE AI</button>
        <div className='mega'>
            <div className='block'>
                    <p>ENTER THE INGREDIENTS</p>
                    <center><input placeholder='Enter Ingredient 1' value={one} onChange={(e)=>setone(e.target.value)} type="text" /></center>
                    <center><input placeholder='Enter Ingredient 2' value={two} onChange={(e)=>settwo(e.target.value)} type="text" /></center>
                    <center><input placeholder='Enter Ingredient 3' value={three} onChange={(e)=>setthree(e.target.value)} type="text" /></center>
                    <center><input placeholder='Enter Ingredient 4' value={four} onChange={(e)=>setfour(e.target.value)} type="text" /></center>
                    <center><input placeholder='Enter Ingredient 5' value={five} onChange={(e)=>setfive(e.target.value)} type="text" /></center>
                <div>
                    <button className='Genbtn' onClick={()=>generateReceipe()}>GENERATE RECEIPE</button>
                </div>
            </div>
            <div className='Res'>
                {loading && <center style={{color:"black" , fontSize:"40px"}}>LOADING....</center>}
                {!loading && response==="" && <center style={{color:"black",fontSize:"30px"}}>HEYYY... <br/><br/> ENTER THE INGREDIENTS YOU HAVE ..</center>}
                {!loading && <RecipeComponent response={response}/>}
            </div>
        </div>
    </>
  )
}

export default ArtificialIntelligence