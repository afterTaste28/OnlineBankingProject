import { useContext } from "react";
import { UserContext } from "../context/UserProvider";
import { Navigate, Outlet } from "react-router-dom";
import TabsComponent from "../TabsComponent";
import { Box, AppBar, Typography } from "@mui/material";
import backgroundImage from '../resources/OnlineBanking.jpg';

const AuthLayout = ()=>{
    const {isLoggedIn} = useContext(UserContext);
    if(isLoggedIn) return (<Navigate to="/dashboard" replace/>)
    return (
        <Box sx={{ minHeight: "100vh",background: `url(${backgroundImage}) no-repeat center center/cover`,
  opacity: 0.9}}>
            <Box mb={10} sx={{ display: "flex" }}>
                <AppBar color="primary" position="fixed" sx={{backgroundColor:'primary.main', zIndex: (theme) => theme.zIndex.drawer + 1 }}>
                    <Typography variant="h6" sx={{ flexGrow: 1 }}>Online Banking</Typography>
                </AppBar>
            </Box>

            <TabsComponent/>
            <Outlet/>
        </Box>
    );
}

export default AuthLayout;