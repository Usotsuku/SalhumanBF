import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import EmployeeService from '../service/EmployeeService';

function EmployeManagementPage() {
  const [employes, setEmployes] = useState([]);

  useEffect(() => {
    fetchEmployes();
  }, []);

  const fetchEmployes = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await EmployeeService.getAllEmployes(token);

      if (response && response.employeDataList) {
        setEmployes(response.employeDataList);
      } else {
        console.error('Invalid response structure:', response);
      }
    } catch (error) {
      console.error('Error fetching employees:', error);
    }
  };

  const deleteEmploye = async (employeId) => {
    try {
      const confirmDelete = window.confirm('Are you sure you want to delete this user?');

      if (confirmDelete) {
        const token = localStorage.getItem('token');
        await EmployeeService.deleteEmploye(employeId, token);
        fetchEmployes();
      }
    } catch (error) {
      console.error('Error deleting user:', error);
    }
  };

  return (
    <div className="user-management-container">
      <h2>Employes Management Page</h2>
      <button className='reg-button'>
        <Link to="/admin/add-employee">Add Employee</Link>
      </button>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Departement</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {employes.map(employe => (
            <tr key={employe.employeId}>
              <td>{employe.employeId}</td>
              <td>{employe.nom}</td>
              <td>{employe.prenom}</td>
              <td>{employe.departement}</td>
              <td>
                <button className='delete-button' onClick={() => deleteEmploye(employe.employeId)}>Delete</button>
                <button>
                  <Link to={`/update-employe/${employe.employeId}`}>Update</Link>
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default EmployeManagementPage;
