import { Routes, Route, Navigate} from 'react-router-dom';
import RegisterForm from '../pages/RegisterForm';

import DashboardPage from '../pages/dashboard/DashboardPage';
import LoginForm from '../pages/LoginForm';
import TabsComponent from '../TabsComponent';
import AuthLayout from '../layouts/AuthLayout';
import DashBoardLayout from '../layouts/DashBoardLayout';
import { useContext } from 'react';
import { UserContext } from '../context/UserProvider';
import SendMoneyPage from '../pages/sendmoney/SendMoneyPage';
import ProfilePage from '../pages/profile/ProfilePage';
import TransactionsPage from '../pages/transactions/TransactionsPage';

const Routing = ()=>{
    const {isLoggedIn} = useContext(UserContext);
    return (
        <Routes>
        
            <Route element={<AuthLayout />}>
                <Route path="/register" element={<RegisterForm/>}/>        
                <Route path="/login" element={<LoginForm/>}/>    
            </Route>

            <Route element={<DashBoardLayout />}>
                <Route path="/dashboard" element={<DashboardPage/>}/>          
                <Route path="/sendmoney" element={<SendMoneyPage/>}/>          
                <Route path="/transactions" element={<TransactionsPage/>}/>          
                <Route path="/profile" element={<ProfilePage/>}/>          
            </Route> 

            <Route path="*" element={isLoggedIn ? 
                                    (<Navigate to="/dashboard" replace/>):
                                    (<Navigate to="/login" replace/>)}/>              
                   
        </Routes>
    )
}

export default Routing;