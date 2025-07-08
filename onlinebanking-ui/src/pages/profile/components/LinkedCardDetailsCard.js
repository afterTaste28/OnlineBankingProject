import { Typography, Box, Card, Grid} from "@mui/material";
const LinkedCardDetailsCard = ({cardDetails})=>{ 
    const getCardMatrix = (stringMatrix) =>{
        return stringMatrix.split(',').reduce((acc, curr)=>{
            const [key,val] = curr.split(':');
            acc[key.trim()] = val.trim();
            return acc;
        },{});
    }

    const cardMatrix = getCardMatrix(cardDetails.cardMatrix);
    

    return (
        <Box mt={4}> {/* Margin Top to separate from personal info */}
              <Typography variant="h6" gutterBottom>
                Linked Debit Card
              </Typography>
               {/* jsx for showing card */}
              <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                <Card 
                  sx={{
                    background: "linear-gradient(135deg, #4e54c8, #8f94fb)",
                    color: "white",
                    padding: 3,
                    borderRadius: 4,
                    maxWidth: 400
                  }}
                >
                <Box display="flex" justifyContent="space-between">
                  <Typography variant="subtitle2">Online Banking</Typography>
                  <Typography variant="subtitle2">Debit</Typography>
                </Box>

                <Box mt={4} mb={2}>
                  <Typography variant="h6" letterSpacing={2}>
                    {cardDetails.cardNumber}
                  </Typography>
                </Box>

                 {/* Card Matrix Grid */}
                <Grid justifyContent="center" container spacing={1}>
                  {Object.entries(cardMatrix).map(([letter, value]) => (
                    <Grid item xs={3} key={letter}>
                      <Box
                        sx={{
                          backgroundColor: "rgba(255,255,255,0.2)",
                          borderRadius: 1,
                          padding: 1,
                          textAlign: "center",
                        }}
                      >
                        <Typography variant="body2" fontWeight="bold">
                          {letter}
                        </Typography>
                        <Typography variant="body2">{value}</Typography>
                      </Box>
                    </Grid>
                  ))}
                </Grid>

                <Box display="flex" justifyContent="space-between">
                  <Box>
                    <Typography variant="caption">Card Holder</Typography>
                    <Typography variant="body1">{cardDetails.cardHolderName}</Typography>
                  </Box>
                  <Box>
                    <Typography variant="caption">Expires</Typography>
                    <Typography variant="body1">{cardDetails.expiryDate}</Typography>
                  </Box>
                </Box>
              </Card>
              </Box> 
        </Box>
    );
}

export default LinkedCardDetailsCard;