import {useState} from 'react';
import { Tabs, Tab, Box } from '@mui/material';
import {useNavigate, useLocation} from 'react-router-dom';

function TabsComponent({setIsLoggedIn}){
    const navigate = useNavigate();
    const location = useLocation();

    const tabVal = location.pathname === '/register' ? 1:0;
    const [currentTab, setCurrentTab] = useState(tabVal);
     
    const handleTabChange = (event, tab)=>{
      setCurrentTab(tab);
      navigate(tab==0?'/login':'/register');
    }
    return (
      <Box sx={{ width: '100%', mt: 4 }}>
        <Tabs value={currentTab} onChange={handleTabChange} centered>
          <Tab sx={{ fontWeight: 'bold', color:"black" }} label="Login" />
          <Tab sx={{ fontWeight: 'bold', color:"black" }} label="Register" />
        </Tabs>

      </Box>
    );
}

export default TabsComponent; 