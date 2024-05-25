import React, { useState } from 'react';
import CongeService from '../service/CongeService'
import { useNavigate } from 'react-router-dom';

function DemanderCongePage() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
      type: '',
      dateDebut: '',
      dateFin: '',
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
          await CongeService.demanderConge(formData,token);

          // Clear the form fields after successful registration
          setFormData({
                type: '',
                datedebut: '',
                datefin: '',
          });
          alert('Conge Created successfully');
          navigate('/employe/demander-conge');

      } catch (error) {
          console.error('Error creating Conge:', error);
          alert('An error occurred while creating conge');
      }
  };

  return (
      <div className="auth-container">
          <h2>Demander Conge</h2>
          <form onSubmit={handleSubmit}>
              <div className="form-group">
                  <label>Type:</label>
                  <input type="text" name="type" value={formData.type} onChange={handleInputChange} required />
              </div>
              <div className="form-group">
                  <label>Date Debut:</label>
                  <input type="date" name="dateDebut" value={formData.dateDebut} onChange={handleInputChange} required />
              </div>
              <div className="form-group">
                  <label>Date Fin:</label>
                  <input type="date" name="dateFin" value={formData.dateFin} onChange={handleInputChange} required />
              </div>
              <button type="submit">Demander</button>
          </form>
      </div>
    );
}
  

export default DemanderCongePage;