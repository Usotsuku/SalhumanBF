import React, { useState } from 'react';
import HeureTravailleeService from '../service/HeureTravailleeService';

export default function AddHeureTravaillee()  {
  const [date] = useState(new Date().toISOString().slice(0, 10)); // Set default date to current day
  const [type, setType] = useState('');
  const [nb_heures, setNb_heures] = useState(8); // Set default number of hours to 8

  const handleSubmit = async (event) => {
    event.preventDefault();
    const token = localStorage.getItem('token');
    const heureTravaillee = {
      date,
      type,
      nb_heures,
      statut: 'Pending' // Set default status to Pending
    };

    try {
      await HeureTravailleeService.addHeureTravaillee(heureTravaillee, token);
      alert('Hours worked added successfully');
    } catch (error) {
      console.error('Error adding hours worked:', error);
    }
  };

  return (
    <div>
      <h2>Add Hours Worked</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Date:</label>
          <input type="text" value={date} readOnly />
        </div>
        <div>
          <label>Type:</label>
          <input type="text" value={type} onChange={(e) => setType(e.target.value)} required />
        </div>
        <div>
          <label>Number of Hours:</label>
          <input type="number" value={nb_heures} onChange={(e) => setNb_heures(e.target.value)} required />
        </div>
        {/* Hidden input for status */}
        <input type="hidden" value="Pending" />
        <button type="submit">Add Hours Worked</button>
      </form>
    </div>
  );
}
