import axios from "axios";

class Service{
    url = "http://localhost:8080/teams"

    getAllTeams(){
        return axios.get(this.url);
    }

    
}

export default new Service();