// App.js
import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Navbar from './components/common/Navbar';
import LoginPage from './components/auth/LoginPage';
import RegistrationPage from './components/auth/RegistrationPage';
import FooterComponent from './components/common/Footer';
import UserService from './components/service/UserService';
import UpdateUser from './components/userspage/UpdateUser';
import UserManagementPage from './components/userspage/UserManagementPage';
import ProfilePage from './components/userspage/ProfilePage';
import AddEmployee from './components/Employee/AddEmployee';
import EmployeManagementPage from './components/Employee/EmployeesManagement';
import UpdateEmployee from './components/Employee/UpdateEmployee';
import DemanderCongePage from './components/Conge/DemanderConge';
import CongeListPage from './components/Conge/EmployeConge';
import ManagerCongesPage from './components/Manager/CongeList';



function App() {

  return (
    <BrowserRouter>
      <div className="App">
        <Navbar />
        <div className="content">
          <Routes>
            <Route exact path="/" element={<LoginPage />} />
            <Route exact path="/login" element={<LoginPage />} />
            <Route path="/profile" element={<ProfilePage />} />

            {/* Check if user is authenticated and admin before rendering admin-only routes */}
            {UserService.adminOnly() && (
              <>
                <Route path="/register" element={<RegistrationPage />} />
                <Route path="/admin/user-management" element={<UserManagementPage />} />
                <Route path="/update-user/:userId" element={<UpdateUser />} />
                <Route path="/admin/add-employee" element={<AddEmployee />} />
                <Route path="/admin/employe-management" element={<EmployeManagementPage />} />
                <Route path="/update-employe/:employeId" element={< UpdateEmployee />} />
                <Route path="/managerCongeList" element={< ManagerCongesPage />} />
              </>
            )}
            {UserService.userOnly() &&(
              <>
              <Route path="/demanderConge" element={< DemanderCongePage />} />
              <Route path="/CongeList" element={< CongeListPage />} />
              </>
            )}
            <Route path="*" element={<Navigate to="/login" />} />‰
          </Routes>
        </div>
        <FooterComponent />
      </div>
    </BrowserRouter>
  );
}

export default App;