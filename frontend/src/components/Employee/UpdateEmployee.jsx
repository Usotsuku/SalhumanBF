import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

import EmployeeService from '../service/EmployeeService';

function UpdateEmploye() {
  const navigate = useNavigate();
  const { employeId } = useParams();


  const [employeData, setEmployeData] = useState({
      nom: '',
      prenom: '',
      departement: '',
      salaire: '',
      poste: '',
      date_embauche: '',
  });

  useEffect(() => {
    fetchEmployeDataById(employeId); // Pass the userId to fetchUserDataById
  }, [employeId]); //wheen ever there is a chane in userId, run this

  const fetchEmployeDataById = async (employeId) => {
    try {
      const token = localStorage.getItem('token');
      const response = await EmployeeService.getEmployeDetails(employeId, token); // Pass userId to getUserById
      const { nom, prenom, departement, salaire, poste, date_embauche } = response.employe;
      setEmployeData({ nom, prenom, departement, salaire, poste, date_embauche  });
    } catch (error) {
      console.error('Error fetching user data:', error);
    }
  };


  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEmployeData((prevEmployeData) => ({
      ...prevEmployeData,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const confirmDelete = window.confirm('Are you sure you want to delete this Employee?');
      if (confirmDelete) {
        const token = localStorage.getItem('token');
        const res = await EmployeeService.updateEmploye(employeId, employeData, token);
        console.log(res)
        // Redirect to profile page or display a success message
        navigate("/admin/employe-management")
      }

    } catch (error) {
      console.error('Error updating user profile:', error);
      alert(error)
    }
  };

  return (
    <div className="auth-container">
        <h2>Update Employee</h2>
        <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label>Nom:</label>
                <input type="text" name="nom" value={employeData.nom} onChange={handleInputChange}  />
            </div>
            <div className="form-group">
                <label>Prenom:</label>
                <input type="text" name="prenom" value={employeData.prenom} onChange={handleInputChange}  />
            </div>
            <div className="form-group">
                <label>departement:</label>
                <input type="text" name="departement" value={employeData.departement} onChange={handleInputChange}  />
            </div>
            <div className="form-group">
                <label>Poste:</label>
                <input type="text" name="poste" value={employeData.poste} onChange={handleInputChange}  />
            </div>
            <div>
                <label>Salaire:</label>
                <input type="number" name="salaire" value={employeData.salaire} onChange={handleInputChange} />
            </div>
            <div>
                <label>Date d'embauche:</label>
                <input type="date" name="date_embauche" value={employeData.date_embauche} onChange={handleInputChange}/>
            </div>
            <button type="submit">Update</button>
        </form>
    </div>
);
}

export default UpdateEmploye;