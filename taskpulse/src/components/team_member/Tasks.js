import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from 'sweetalert2';
import { MemberContext } from "../team_member/Navmember";



export function Tasksmember(){
  
  const { teamId, userid } = useContext(MemberContext);


    const [tasks, setTasks] = useState([]);
    const navigate = useNavigate()

    useEffect(() => {
        axios.get(`http://localhost:8080/tasksByUserId/${userid}`)
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

    const updateTask = (taskid) =>{

      Swal.fire({
        title: 'Update Task Status',
        text: 'Choose the new status for this task:',
        icon: 'question',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: 'To Do',
        denyButtonText: 'In Progress',
        cancelButtonText: 'Done',
        customClass: {
          confirmButton: 'btn btn-secondary',
          denyButton: 'btn btn-warning',
          cancelButton: 'btn btn-success'
        },
        buttonsStyling: false, // Use Bootstrap classes
      }).then((result) => {
        if (result.isConfirmed) {
          const status = "TODO"
          axios.put(`http://localhost:8080/updateTask/${taskid}/${status}`)
                .then(() => {
                  Swal.fire('Updated!', 'The ststus has been updated.', 'success');
                  setTimeout(() => {
                    window.location.reload();
                  }, 1000);
                })
                .catch(error => {
                  console.error('Error udpading task:', error);
                });
        } else if (result.isDenied) {
          const status = "IN_PROGRESS"
          axios.put(`http://localhost:8080/updateTask/${taskid}/${status}`)
                .then(() => {
                  Swal.fire('Updated!', 'The ststus has been updated.', 'success');
                  setTimeout(() => {
                    window.location.reload();
                  }, 1000);
                })
                .catch(error => {
                  console.error('Error udpading task:', error);
                });
        } else if (result.dismiss === Swal.DismissReason.cancel) {
          const status = "DONE"
          axios.put(`http://localhost:8080/updateTask/${taskid}/${status}`)
                .then(() => {
                  Swal.fire('Updated!', 'The status has been updated.', 'success');
                  setTimeout(() => {
                    window.location.reload();
                  }, 1000);
                })
                .catch(error => {
                  console.error('Error udpading task:', error);
                });
        }
      });
    }

    return <div>
       
    <h2>Tasks Assigned</h2>
    <table className="table table-striped">
        <thead>
            <tr>
                <th scope="col">TITLE</th>
                <th scope="col">DESCRIPTION</th>
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
      <td>{task.status}</td>
      <td><a href="#!" onClick={() => updateTask(task.id)} className="text-primary" title="Update Task">
  <i className="fas fa-edit"></i>
</a>
</td>
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