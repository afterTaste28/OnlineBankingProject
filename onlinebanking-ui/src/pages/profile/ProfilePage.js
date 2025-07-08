import { Container, Typography, Box, Button, Card, Grid} from "@mui/material";
import ProfileCard from "./components/ProfileCard";
import LinkedCardDetailsCard from "./components/LinkedCardDetailsCard";
import { useState, useEffect } from "react";
import EditProfileDialog from "./components/EditProfileDialog";
import UpdatePasswordDialog from "./components/UpdatePasswordDialog";
import {getUserInfo, getCardDetails} from "../../api/APIRegistry";


const ProfilePage = () => {
    // const [editOpen, setEditOpen] = useState(false);
    // const [passwordOpen, setPasswordOpen] = useState(false);
    const [userInfo,setUserInfo] = useState(null);
    const [cardDetails,setCardDetails] = useState(null);
    const [error,setError] = useState(null);
    const [isLoading,setLoading] = useState(true);

    useEffect(() => {
      const fetchData = async () => {
        try {
          const [userRes, cardRes] = await Promise.all([
            getUserInfo(),
            getCardDetails()
          ]);

          setUserInfo(userRes.data);
          setCardDetails(cardRes.data);
        } catch (err) {
          console.error('Error fetching data:', err);
          setError('Could not load profile or card details');
        } finally {
          setLoading(false); 
        }
      };

      fetchData();
    }, []);

    

    
    if (isLoading) {
      return (
        <Container>
          <Typography variant="h6">Loading...</Typography>
        </Container>
      );
    }

    if (error) {
      return (
        <Container>
          <Typography variant="h6" color="error">{error}</Typography>
        </Container>
      );
    }
    
    return (
        <Container maxWidth="md" sx={{mt:4}}>
            <Typography variant="h4" gutterBottom>
                My Profile
            </Typography>

            <ProfileCard userInfo={userInfo}></ProfileCard>
            <LinkedCardDetailsCard cardDetails={cardDetails}></LinkedCardDetailsCard>

            


            {/* <Box sx={{ mt: 3, display: 'flex', gap: 2 }}>
                <Button variant="contained" onClick={() => setEditOpen(true)}>
                Edit Profile
                </Button>
                <Button variant="outlined" onClick={() => setPasswordOpen(true)}>
                Update Password
                </Button>
            </Box> */}

            {/* <EditProfileDialog
                open={editOpen}
                onClose={() => setEditOpen(false)}
                user={user}
                setUser={setUser}
            />

            <UpdatePasswordDialog
                open={passwordOpen}
                onClose={() => setPasswordOpen(false)}
            /> */}
        </Container>
        
    );
}

export default ProfilePage;