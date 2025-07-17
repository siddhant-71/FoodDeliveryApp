import './App.css'
import{BrowserRouter,Routes,Route}from 'react-router-dom'
import Login from "./pages/Login.jsx"
import Register from "./pages/Register.jsx"
import ProtectedRoute from './pages/ProtectedRoute.jsx'
import Dashboard from "./pages/Dashboard.jsx"
import TrackOrder from './pages/TrackOrder.jsx'
import OrderHistory from './pages/OrderHistory.jsx'
import Restaurants from './pages/Restaurants.jsx'
import DashboardData from './pages/DashboardData.jsx'
import Cart from './pages/Cart.jsx'

function App() {
    let Name=localStorage.getItem('name');
    let Id=localStorage.getItem('id');
    let Email=localStorage.getItem('email');
    let Role=localStorage.getItem('role');
    let PhoneNo=localStorage.getItem('phone');

  return (
    <BrowserRouter>
      <Routes>
          <Route path="/" element={<Login/>}/>
          <Route path="/register" element={<Register/>}/>
          <Route path="/dashboard" element={  <ProtectedRoute> <Dashboard/> </ProtectedRoute>} >
            <Route index element={<DashboardData/>}/> 
            <Route path='restaurant' element={<Restaurants/>}/>
            <Route path='track' element={<TrackOrder/>}/>
            <Route path='history' element={<OrderHistory/>}/>
            <Route path='cart' element={<Cart/>}/>
          </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
