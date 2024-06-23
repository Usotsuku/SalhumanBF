import React, { useState, useEffect, useCallback } from 'react';
import { useParams } from 'react-router-dom';
import HeureTravailleeService from '../service/HeureTravailleeService';

function HoursWorked() {
  const { employeId } = useParams();
  const [heures, setHeures] = useState([]);
  const [viewMode, setViewMode] = useState('week');

  const fetchHeures = async () => {
    try {
      const token = localStorage.getItem('token');
      let response;
      if (viewMode === 'week') {
        response = await HeureTravailleeService.getHeuresByEmployeAndWeek(employeId, token);
      } else if (viewMode === 'month') {
        response = await HeureTravailleeService.getHeuresByEmployeAndMonth(employeId, token);
      }
      if (response && response.data) {
        setHeures(response.data);
        console.log(response.data); // Add this line to check the value of response.data
      } else {
        console.error('Invalid response structure:', response);
      }
    } catch (error) {
      console.error('Error fetching hours worked:', error);
    }
  };

  useEffect(() => {
    fetchHeures();
  }, [viewMode, fetchHeures]);

  return (
    <div className="hours-worked-container">
      <h2>Hours Worked by Employee {employeId}</h2>
      <div>
        <button onClick={() => setViewMode('week')}>Week</button>
        <button onClick={() => setViewMode('month')}>Month</button>
      </div>
      <div>
        {heures.map(heure => (
          <div key={heure.HeureId}>
            <p>{heure.date}: {heure.nb_heures} hours ({heure.type})</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default HoursWorked;
