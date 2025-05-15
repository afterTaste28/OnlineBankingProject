import { Tabs, Tab, Box, Typography } from '@mui/material';
import {useNavigate, useLocation} from 'react-router-dom';

function TabsComponent(){

    //const RegisterForm = () => <Typography>Register Placeholder</Typography>;
    const navigate = useNavigate();
    const location = useLocation();

    const currentTab = location.pathname === '/register' ? 1:0;
     
    const handleTabChange = (event, tab)=>{
        navigate(tab==0?'/login':'/register');
    }
    return (
      <Box sx={{ width: '100%', mt: 4 }}>
        <Tabs value={currentTab} onChange={handleTabChange} centered>
          <Tab label="Login" />
          <Tab label="Register" />
        </Tabs>

      </Box>
    );
}

export default TabsComponent; 