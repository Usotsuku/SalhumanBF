import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Box, Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Typography } from '@mui/material';
import UserService from '../service/UserService';
import Sidebar from '../common/Sidebar';

function UserManagementPage() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    // Fetch users data when the component mounts
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const token = localStorage.getItem('token'); // Retrieve the token from localStorage
      const response = await UserService.getAllUsers(token);
      //   console.log(response);
      if (response && response.userDataList) {
        setUsers(response.userDataList);
      } else {
        console.error('Invalid response structure:', response);
      } // Assuming the list of users is under the key 'ourUsersList'
    } catch (error) {
      console.error('Error fetching users:', error);
    }
  };

  const deleteUser = async (userId) => {
    try {
      // Prompt for confirmation before deleting the user
      const confirmDelete = window.confirm('Are you sure you want to delete this user?');

      const token = localStorage.getItem('token'); // Retrieve the token from localStorage
      if (confirmDelete) {
        await UserService.deleteUser(userId, token);
        // After deleting the user, fetch the updated list of users
        fetchUsers();
      }
    } catch (error) {
      console.error('Error deleting user:', error);
    }
  };

  return (
    <Box sx={{ display: "flex", backgroundColor: "cyan", minHeight: "100vh" }}>
      <Sidebar />
      <Box sx={{ flexGrow: 1, p: 3 }}>
        <Typography variant="h4" gutterBottom>
          Users Management Page
        </Typography>
        <Button variant="contained" color="primary" component={Link} to="/register" sx={{ mb: 2 }}>
          Add User
        </Button>
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Name</TableCell>
                <TableCell>Email</TableCell>
                <TableCell>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {users.map(user => (
                <TableRow key={user.id}>
                  <TableCell>{user.id}</TableCell>
                  <TableCell>{user.name}</TableCell>
                  <TableCell>{user.email}</TableCell>
                  <TableCell>
                    <Button
                      variant="contained"
                      color="secondary"
                      onClick={() => deleteUser(user.id)}
                      sx={{ mr: 1 }}
                    >
                      Delete
                    </Button>
                    <Button
                      variant="contained"
                      component={Link}
                      to={`/update-user/${user.id}`}
                      sx={{ mr: 1 }}
                    >
                      Update
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
    </Box>
  );
}

export default UserManagementPage;
