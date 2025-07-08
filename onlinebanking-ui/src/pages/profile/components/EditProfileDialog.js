// src/components/EditProfileDialog.jsx
import React, { useState } from 'react';
import {
  Dialog, DialogTitle, DialogContent, DialogActions,
  TextField, Button, Box
} from '@mui/material';

const EditProfileDialog = ({ open, onClose, user, setUser }) => {
  const [formData, setFormData] = useState(user);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSave = () => {
    setUser(formData); // Normally you'd make API call here
    onClose();
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Edit Profile</DialogTitle>
      <DialogContent>
        <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 1 }}>
          <TextField label="Name" name="name" value={formData.name} onChange={handleChange} />
          <TextField label="Email" name="email" value={formData.email} onChange={handleChange} />
          <TextField label="Phone" name="phone" value={formData.phone} onChange={handleChange} />
          <TextField label="Address" name="address" value={formData.address} onChange={handleChange} />
        </Box>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>Cancel</Button>
        <Button variant="contained" onClick={handleSave}>Save</Button>
      </DialogActions>
    </Dialog>
  );
};

export default EditProfileDialog;
