import axios from "axios";

class EmployeeService{
    static BASE_URL = "http://localhost:8080"


    static async addEmploye(employeData, token){
        try{
            const response = await axios.post(`${EmployeeService.BASE_URL}/admin/create-employe`, employeData, 
            {
                headers: {Authorization: `Bearer ${token}`}
            })
            return response.data;
        }catch(err){
            throw err;
        }
    }

    static async getAllEmployes(token){
        try{
            const response = await axios.get(`${EmployeeService.BASE_URL}/admin/get-all-employes`, 
            {
                headers: {Authorization: `Bearer ${token}`}
            })
            return response.data;
        }catch(err){
            throw err;
        }
    }

    static async getEmployeDetails(employeId, token){
        try{
            const response = await axios.get(`${EmployeeService.BASE_URL}/admin/get-employe/${employeId}`, 
            {
                headers: {Authorization: `Bearer ${token}`}
            })
            return response.data;
        }catch(err){
            throw err;
        }
    }

    static async deleteEmploye(employeId, token){
        try{
            const response = await axios.delete(`${EmployeeService.BASE_URL}/admin/delete-employe/${employeId}`, 
            {
                headers: {Authorization: `Bearer ${token}`}
            })
            return response.data;
        }catch(err){
            throw err;
        }
    }

    static async updateEmploye(employeId, employeData, token){
        try{
            const response = await axios.put(`${EmployeeService.BASE_URL}/admin/update-employe/${employeId}`, employeData,
            {
                headers: {Authorization: `Bearer ${token}`}
            })
            return response.data;
        }catch(err){
            throw err;
        }
    }
}

export default EmployeeService;