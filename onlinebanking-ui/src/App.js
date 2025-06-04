
import './App.css';
import { Routes, Route, Navigate} from 'react-router-dom';
// import { Navigate } from 'react-router-dom';
import TabsComponent from './TabsComponent';
import RegisterForm from './pages/RegisterForm';
import LoginForm from './pages/LoginForm';
import DashboardPage from './pages/DashboardPage';


function App() {
  return (
    <div className="App">
      <TabsComponent/>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />

        <Route path="/register" element={<RegisterForm/>}/>        
        <Route path="/login" element={<LoginForm/>}/>                 
        <Route path="/dashboard" element={<DashboardPage/>}/>                 
      </Routes>
    </div>
  );
}

export default App;
