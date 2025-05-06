import axios from "axios";
import { useEffect, useState } from "react";

export function Teams(){

    const [teams, setTeams] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8081/teams')
            .then(response => {
                setTeams(response.data); // Store data in state
            })
            .catch(error => {
                console.error('Error fetching users:', error);
            });
    }, []);

    return <div>
    <h2>Teams</h2>
    <table className="table table-striped">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">DESCRIPTION</th>
            </tr>
        </thead>
        <tbody>
            {teams.map((team) => (
                <tr key={team.id}>
                    <td>{team.id}</td>
                    <td>{team.name}</td>
                    <td>{team.description}</td>
                </tr>
            ))}
        </tbody>
    </table>
</div>


}