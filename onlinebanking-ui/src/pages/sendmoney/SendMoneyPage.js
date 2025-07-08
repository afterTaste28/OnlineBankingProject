import React, { useState } from "react";
import {
  Container,
  Typography,
  TextField,
  Button,
  Box,
  Alert
} from "@mui/material";
import { sendMoney } from "../../api/APIRegistry";

const SendMoneyPage = () => {
  const [formData, setFormData] = useState({
    recipientAccount: "",
    description: "",
    amount: ""
  });
  const initiateTransaction = async () => {
    try {
      const response = await sendMoney(formData);
      if(response.data.transactionStatus == "SUCCESS"){
        setSuccess("Money sent successfully! Transaction id :"+response.data.id);
        setError("");
        setFormData({
          recipientAccount: "",
          description: "",
          amount: ""
        });
      } else {
        setError(response.data.transactionStatusMessage +"! Transaction id :"+response.data.id);
        setSuccess("");
      }  
    } catch (err) {
      //console.error('Error fetching balance:', err.response.data.errorString);
      setError("Failed to send money.");
      setSuccess("");
    }
  };

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Basic validation
    if (!formData.recipientAccount || !formData.amount) {
      setError("Please fill in all required fields.");
      setSuccess("");
      return;
    }
    console.log("Sending money:", formData);
      initiateTransaction(formData);
  };

  return (
    <Container maxWidth="sm" sx={{ mt: 5 }}>
      <Typography variant="h4" gutterBottom>
        Send Money
      </Typography>

      {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}
      {success && <Alert severity="success" sx={{ mb: 2 }}>{success}</Alert>}

      <Box
        component="form"
        onSubmit={handleSubmit}
        sx={{ display: "flex", flexDirection: "column", gap: 2 }}
      >
        <TextField
          label="Recipient Account Number"
          name="recipientAccount"
          value={formData.recipientAccount}
          onChange={handleChange}
          fullWidth
          required
        />

        <TextField
          label="Description"
          name="description"
          value={formData.description}
          onChange={handleChange}
          fullWidth
        />

        <TextField
          label="Amount"
          name="amount"
          type="number"
          value={formData.amount}
          onChange={handleChange}
          fullWidth
          required
        />

        <Button variant="contained" type="submit" color="primary">
          Send
        </Button>
      </Box>
    </Container>
  );
};

export default SendMoneyPage;
