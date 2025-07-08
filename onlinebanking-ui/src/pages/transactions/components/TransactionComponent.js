import {
  Box,
  Typography,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper
} from '@mui/material';

const TransactionComponent = ({transactions})=>{    

    return (
        <Box mt={4}>
      <Typography variant="h6" gutterBottom>
        Transactions
      </Typography>

      

      {transactions.length === 0 ? (
  <Typography color="text.secondary">No transactions found.</Typography>
        ) : (
        <TableContainer component={Paper} sx={{ borderRadius: 2, boxShadow: 2 }}>
            <Table>
            <TableHead sx={{ backgroundColor: '#f5f5f5' }}>
                <TableRow>
                <TableCell><strong>Transaction Id</strong></TableCell>
                <TableCell><strong>Description</strong></TableCell>
                <TableCell><strong>Amount</strong></TableCell>
                <TableCell><strong>Date</strong></TableCell>
                <TableCell><strong>Transaction Type</strong></TableCell>
                <TableCell><strong>Status</strong></TableCell>
                <TableCell><strong>Transaction Message</strong></TableCell>
                </TableRow>
            </TableHead>

            <TableBody>
                {transactions.map((txn) => (
                <TableRow key={txn.id}>
                    <TableCell>{txn.id}</TableCell>
                    <TableCell>{txn.description}</TableCell>

                    <TableCell>
                    <Typography
                        color={txn.transactionStatus?.toUpperCase() === 'FAILED' ? 'error' : 'primary'}
                        fontWeight="bold"
                    >
                        ₹{txn.amount}
                    </Typography>
                    </TableCell>

                    <TableCell>{new Date(txn.timestamp).toLocaleString()}</TableCell>

                    <TableCell>{txn.transactionType}</TableCell>
                    <TableCell>{txn.transactionStatus}</TableCell>
                    <TableCell>{txn.transactionStatusMessage}</TableCell>
                </TableRow>
                ))}
            </TableBody>
            </Table>
        </TableContainer>
        )}

    </Box>
    );
}

export default TransactionComponent;