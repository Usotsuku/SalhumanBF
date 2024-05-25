import React, { useState, useEffect } from 'react';
import CongeService from '../service/CongeService';

function ManagerCongesPage() {
    const [conges, setConges] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchConges = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await CongeService.getAllConges(token);
                setConges(response.congeDataList || []);
            } catch (err) {
                setError('Failed to fetch conges');
            }
        };
        fetchConges();
    }, []);

    const handleApprove = async (congeId) => {
        try {
            const token = localStorage.getItem('token');
            await CongeService.approuverConge(congeId, token);
            setConges((prevConges) =>
                prevConges.map((conge) =>
                    conge.congeId === congeId ? { ...conge, statuts: 'Approved' } : conge
                )
            );
        } catch (err) {
            setError('Failed to approve conge');
        }
    };

    return (
        <div>
            <h2>All Conges</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Type</th>
                        <th>Date Debut</th>
                        <th>Date Fin</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {conges.map((conge) => (
                        <tr key={conge.congeId}>
                            <td>{conge.congeId}</td>
                            <td>{conge.type}</td>
                            <td>{new Date(conge.dateDebut).toLocaleDateString()}</td>
                            <td>{new Date(conge.dateFin).toLocaleDateString()}</td>
                            <td>{conge.statuts}</td>
                            <td>
                                {conge.statuts !== 'Approved' && (
                                    <button onClick={() => handleApprove(conge.congeId)}>Approve</button>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default ManagerCongesPage;
