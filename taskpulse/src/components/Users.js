import axios from "axios";
import { useEffect, useState } from "react";

export function Users(){

    const [users, setUsers] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/users')
            .then(response => {
                setUsers(response.data); // Store data in state
            })
            .catch(error => {
                console.error('Error fetching users:', error);
            });
    }, []);

    return <div>
    <button className="btn btn-secondary">ADD NEW USER</button>
        <hr/>
    <h2>MEMBERS</h2>
    <table className="table table-striped">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Role</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            {users.map((user) => (
                <tr key={user.id}>
                    <td>{user.id}</td>
                    <td>{user.name}</td>
                    <td>{user.email}</td>
                    <td>{user.role}</td>
                    <td><a href="#!"  class="text-danger" data-mdb-tooltip-init title="Delete todo"><i
                      class="fas fa-trash-alt"></i></a></td>
                </tr>
            ))}
        </tbody>
    </table>
</div>


}