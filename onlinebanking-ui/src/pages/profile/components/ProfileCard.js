import { Card, CardContent, Grid, Typography, Box } from "@mui/material";

const ProfileCard = ({userInfo})=>{
    return (
        <Card elevation={3}>
            <CardContent>
                <Typography variant="h6" align="center" gutterBottom mb={5}>
                    Personal Information
                </Typography>
                <Grid container spacing={2}>
                    <Grid item xs={4}>
                        <Box display="flex" flexDirection="column" gap={1}>
                        <Typography variant="body1" textAlign="left"><strong>Name:</strong></Typography>
                        <Typography variant="body1" textAlign="left"><strong>Email:</strong></Typography>
                        <Typography variant="body1" textAlign="left"><strong>Phone:</strong></Typography>
                        <Typography variant="body1" textAlign="left"><strong>Address:</strong></Typography>
                        </Box>
                    </Grid>

                    {/* Values Column */}
                    <Grid item xs={8}>
                        <Box display="flex" flexDirection="column" gap={1}>
                        <Typography variant="body1" textAlign="left">{userInfo.name}</Typography>
                        <Typography variant="body1" textAlign="left">{userInfo.email}</Typography>
                        {/* <Typography variant="body1" textAlign="left">{user.phone}</Typography>
                        <Typography variant="body1" textAlign="left">{user.address}</Typography> */}
                        </Box>
                    </Grid>
                </Grid>
            </CardContent>
        </Card>
    );
}

export default ProfileCard;