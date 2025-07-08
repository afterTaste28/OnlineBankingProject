import React, { useEffect, useState } from 'react';
import {
  Table, TableBody, TableCell, TableHead, TableRow, Paper,
  TableContainer, TablePagination, Typography, Chip, CircularProgress
} from '@mui/material'
import { getTransactions } from '../../api/APIRegistry';
import TransactionComponent from './components/TransactionComponent';

export default function TransactionsPage() {
  const [transactions, setTransactions] = useState([]);
  const [page, setPage] = useState(0); // page number
  const [rowsPerPage, setRowsPerPage] = useState(10); // page size
  const [totalElements, setTotalElements] = useState(0);
  const [isTransactionsLoading, setIsTransactionsLoading] = useState(false);

  useEffect(() => {
    fetchTransactions(page, rowsPerPage);
  }, [page, rowsPerPage]);

  const fetchTransactions = async (page, size) => {
    setIsTransactionsLoading(true);
    try {
      const res = await getTransactions(page,size);
      setTransactions(res.data.content); // `content` is the list
      setTotalElements(res.data.totalElements);
    } catch (err) {
      console.error("Error fetching transactions", err);
    } finally {
      setIsTransactionsLoading(false);
    }
  };

  const handleChangePage = (event, newPage) => setPage(newPage);
  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <Paper sx={{ p: 3, borderRadius: 2, boxShadow: 3 }}>
      <Typography variant="h5" gutterBottom>
        All Transactions
      </Typography>
      {isTransactionsLoading ? (<CircularProgress/>)
            : (<TransactionComponent transactions={transactions}></TransactionComponent>)}
    
      <TablePagination
            component="div"
            count={totalElements}
            page={page}
            onPageChange={handleChangePage}
            rowsPerPage={rowsPerPage}
            onRowsPerPageChange={handleChangeRowsPerPage}
            rowsPerPageOptions={[5, 10, 20]}
      />
    </Paper>
  );
}

