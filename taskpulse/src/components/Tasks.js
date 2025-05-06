import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2';


export function Tasks(){



    const [tasks, setTasks] = useState([]);
    const navigate = useNavigate()

    useEffect(() => {
        axios.get('http://localhost:8081/tasks')
            .then(response => {
                setTasks(response.data); // Store data in state
            })
            .catch(error => {
                console.error('Error fetching users:', error);
            });
    }, []);

    const handleClick = () => {
      navigate("/addtask"); // relative path
    };

    // DELETE TASK========================

    const deletetask = (taskid) =>{

      Swal.fire({
        title: 'Are you sure?',
        text: 'You won’t be able to undo this!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33', // red
        cancelButtonColor: '#6c757d',
        confirmButtonText: 'Yes, delete it!',
      }).then((result) => {
        if (result.isConfirmed) {
          axios.delete(`http://localhost:8081/deletTask/${taskid}`)
              .then(() => {
                Swal.fire('Deleted!', 'The task has been removed.', 'success');
                setTimeout(() => {
                  window.location.reload();
                }, 1000);
              })
              .catch(error => {
                console.error('Error fetching users:', error);
              });
          
        }
      });

        
      
    }

    return <div>
        <button className="btn btn-secondary" onClick={handleClick}>ADD NEW TASK</button>
        <hr/>
    <h2>Tasks</h2>
    <table className="table table-striped">
        <thead>
            <tr>
                <th scope="col">TITLE</th>
                <th scope="col">DESCRIPTION</th>
                <th scope="col">USER ASSIGNED</th>
                <th scope="col">STATUS</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
        {tasks && tasks.length > 0 ? (
  tasks.map((task) => (
    <tr key={task.id}>
      <td>{task.title}</td>
      <td>{task.description}</td>
      <td>{Array.isArray(task.users) && task.users.length > 0 ? task.users[0].name : "No user assigned"}</td>
      <td>{task.status}</td>
      <td><a href="#!" onClick={() => deletetask(task.id)}  class="text-danger" data-mdb-tooltip-init title="Delete Task"><i
                      class="fas fa-trash-alt"></i></a></td>
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