import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import CongeService from '../service/CongeService';

function CongeListPage() {
  const [conges, setConges] = useState([]);

  useEffect(() => {
    fetchConges();
  }, []);

  const fetchConges = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await CongeService.getAllEmployeeConges(token);

      if (response && response.congeDataList) {
        setConges(response.congeDataList);
      } else {
        console.error('Invalid response structure:', response);
      }
    } catch (error) {
      console.error('Error fetching Conges:', error);
    }
  };


  return (
    <div className="user-management-container">
      <h2>Conges</h2>
      <button className='reg-button'>
        <Link to="/employe/demander-conge">Demander Conge</Link>
      </button>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Date Debut</th>
            <th>Date Fin</th>
            <th>Statuts</th>
          </tr>
        </thead>
        <tbody>
          {conges.map(conge => (
            <tr key={conge.congeId}>
              <td>{conge.congeId}</td>
              <td>{conge.type}</td>
              <td>{conge.dateDebut}</td>
              <td>{conge.dateFin}</td>
              <td>{conge.statuts}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CongeListPage;
