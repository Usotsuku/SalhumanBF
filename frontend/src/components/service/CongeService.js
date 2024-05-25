import axios from "axios";

class CongeService{
    static BASE_URL = "http://localhost:8080"


    static async demanderConge(congeData, token){
        try{
            const response = await axios.post(`${CongeService.BASE_URL}/employe/demander-conge`, congeData, 
            {
                headers: {Authorization: `Bearer ${token}`}
            })
            return response.data;
        }catch(err){
            throw err;
        }
    }

    static async getAllEmployeeConges(token){
        try{
            const response = await axios.get(`${CongeService.BASE_URL}/employe/get-all-conges`, 
            {
                headers: {Authorization: `Bearer ${token}`}
            })
            return response.data;
        }catch(err){
            throw err;
        }
    }

    static async getAllConges(token){
        try{
            const response = await axios.get(`${CongeService.BASE_URL}/manager/get-all-conges`, 
            {
                headers: {Authorization: `Bearer ${token}`}
            })
            return response.data;
        }catch(err){
            throw err;
        }
    }

    static async approuverConge(congeId, token) {
        try {
            const response = await axios.put(`${CongeService.BASE_URL}/manager/approuver-conge/${congeId}`, {}, {
                headers: { Authorization: `Bearer ${token}` }
            });
            return response.data;
        } catch (err) {
            console.error('Error in approuverConge:', err.response || err.message);
            throw err;
        }
    }

}

export default CongeService;