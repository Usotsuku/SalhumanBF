import React from 'react';
import { Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import Sidebar from '../common/Sidebar';

function PayslipPage() {
  // Static payslip data
  const payslip = {
    firstName: 'John',
    lastName: 'Doe',
    period: 'March 2024',
    grossAmount: 5000,
    netAmount: 4000,
    amo: 300,
    cnss: 200,
    incomeTax: 500
  };

  return (
    <Box sx={{ display: "flex", backgroundColor: "cyan", minHeight: "100px" }}>
      <Sidebar />
      <Box sx={{ flexGrow: 1, p: 3 }}>
        <Typography variant="h4" gutterBottom>
          Payslip for {payslip.firstName} {payslip.lastName} - {payslip.period}
        </Typography>
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Element</TableCell>
                <TableCell>Amount</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              <TableRow>
                <TableCell>Period</TableCell>
                <TableCell>{payslip.period}</TableCell>
              </TableRow>
              <TableRow>
                <TableCell>Gross Amount</TableCell>
                <TableCell>{payslip.grossAmount}</TableCell>
              </TableRow>
              <TableRow>
                <TableCell>Net Amount</TableCell>
                <TableCell>{payslip.netAmount}</TableCell>
              </TableRow>
              <TableRow>
                <TableCell>AMO</TableCell>
                <TableCell>{payslip.amo}</TableCell>
              </TableRow>
              <TableRow>
                <TableCell>CNSS</TableCell>
                <TableCell>{payslip.cnss}</TableCell>
              </TableRow>
              <TableRow>
                <TableCell>Income Tax</TableCell>
                <TableCell>{payslip.incomeTax}</TableCell>
              </TableRow>
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
    </Box>
  );
}

export default PayslipPage;
