import { useContext } from "react";
import { UserContext } from "../context/UserProvider";
import { Navigate, Outlet, useNavigate } from "react-router-dom";
import {
  Box,
  Drawer,
  AppBar,
  Toolbar,
  Typography,
  Button,
  List,
  ListItem,
  ListItemText,
} from "@mui/material";

const drawerWidth = 240;

const DashBoardLayout = () => {
  const { isLoggedIn, setIsLoggedIn } = useContext(UserContext);
  const navigate = useNavigate();

  if (!isLoggedIn) return <Navigate to="/login" replace />;

  const handleLogout = () => {
    localStorage.removeItem("token");
    setIsLoggedIn(false);
    navigate("/login");
  };

  const menuItems = [
    { label: "Dashboard", path: "/dashboard" },
    { label: "Send Money", path: "/sendmoney" },
    { label: "Transactions", path: "/transactions" },
    { label: "Profile", path: "/profile" },
  ];

  return (
    <Box sx={{ display: "flex" }}>
      <AppBar color="primary" position="fixed" sx={{backgroundColor:'primary.main', zIndex: (theme) => theme.zIndex.drawer + 1 }}>
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>Online Banking</Typography>
          <Button variant="contained" color="secondary" onClick={handleLogout}>Logout</Button>
        </Toolbar>
      </AppBar>

      <Drawer
        variant="permanent"
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          [`& .MuiDrawer-paper`]: { width: drawerWidth, boxSizing: "border-box" },
        }}
      >
        <Toolbar />
        <List>
          {menuItems.map((item) => (
            <ListItem button key={item.label} onClick={() => navigate(item.path)}>
              <ListItemText primary={item.label} />
            </ListItem>
          ))}
        </List>
      </Drawer>

      <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
        <Toolbar />
        <Outlet /> {/* 👈 This renders the specific dashboard/account page */}
      </Box>
    </Box>
  );
};

export default DashBoardLayout;
