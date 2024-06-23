import React from 'react';
import { Navigate } from 'react-router-dom';
import UserService from '../components/service/UserService';

function PrivateRoute({ children, role }) {
  if (!UserService.isAuthenticated()) {
    return <Navigate to="/login" />;
  }

  if (role === 'admin' && !UserService.isAdmin()) {
    return <Navigate to="/profile" />;
  }

  if (role === 'user' && !UserService.isUser()) {
    return <Navigate to="/profile" />;
  }

  return children;
}

export default PrivateRoute;
