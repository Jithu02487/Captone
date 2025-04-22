import axios from "axios";
import { useEffect, useState } from "react";

export function Tasks(){



    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/tasks')
            .then(response => {
                setTasks(response.data); // Store data in state
            })
            .catch(error => {
                console.error('Error fetching users:', error);
            });
    }, []);


    return <div>
        <button className="btn btn-secondary">ADD NEW TASK</button>
        <hr/>
    <h2>Tasks</h2>
    <table className="table table-striped">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">TITLE</th>
                <th scope="col">DESCRIPTION</th>
                <th scope="col">USER ASSIGNED</th>
                <th scope="col">STATUS</th>
            </tr>
        </thead>
        <tbody>
        {tasks && tasks.length > 0 ? (
  tasks.map((task) => (
    <tr key={task.id}>
      <td>{task.id}</td>
      <td>{task.title}</td>
      <td>{task.description}</td>
      <td>{Array.isArray(task.users) && task.users.length > 0 ? task.users[0].name : "No user assigned"}</td>
      <td>{task.status}</td>
    </tr>
  ))
) : (
  <tr>
    <td colSpan="4">No tasks found.</td>
  </tr>
)}
        </tbody>
    </table>
</div>
}