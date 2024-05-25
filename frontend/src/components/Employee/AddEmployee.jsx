import React, { useState } from 'react';
import EmployeeService from '../service/EmployeeService'
import { useNavigate } from 'react-router-dom';

function CreateEmployeePage() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
      nom: '',
      prenom: '',
      departement: '',
      salaire: '',
      poste: '',
      date_embauche: '',
  });

  const handleInputChange = (e) => {
      const { name, value } = e.target;
      setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
      e.preventDefault();
      try {
          // Call the register method from UserService

          const token = localStorage.getItem('token');
          await EmployeeService.addEmploye(formData,token);

          // Clear the form fields after successful registration
          setFormData({
              nom: '',
              prenom: '',
              departement: '',
              salaire: '',
              poste: '',
              date_embauche: '',
          });
          alert('Employee created successfully');
          navigate('/admin/user-management');

      } catch (error) {
          console.error('Error creating employee:', error);
          alert('An error occurred while creating employee');
      }
  };

  return (
      <div className="auth-container">
          <h2>Create Employee</h2>
          <form onSubmit={handleSubmit}>
              <div className="form-group">
                  <label>Nom:</label>
                  <input type="text" name="nom" value={formData.nom} onChange={handleInputChange} required />
              </div>
              <div className="form-group">
                  <label>Prenom:</label>
                  <input type="text" name="prenom" value={formData.prenom} onChange={handleInputChange} required />
              </div>
              <div className="form-group">
                  <label>departement:</label>
                  <input type="text" name="departement" value={formData.departement} onChange={handleInputChange} required />
              </div>
              <div className="form-group">
                  <label>Poste:</label>
                  <input type="text" name="poste" value={formData.poste} onChange={handleInputChange} required />
              </div>
              <div>
                  <label>Salaire:</label>
                  <input type="number" name="salaire" value={formData.salaire} onChange={handleInputChange} required />
              </div>
              <div>
                  <label>Date d'embauche:</label>
                  <input type="date" name="date_embauche" value={formData.date_embauche} onChange={handleInputChange} required />
              </div>
              <button type="submit">Add employee</button>
          </form>
      </div>
  );
}

export default CreateEmployeePage;