import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

export function Users(){

    const [users, setUsers] = useState([]);
    const nav = useNavigate()

    useEffect(() => {
        axios.get('http://localhost:8081/users')
            .then(response => {
                setUsers(response.data); // Store data in state
            })
            .catch(error => {
                console.error('Error fetching users:', error);
            });
    }, []);

    const addUser=()=>{
        nav("/adduser")
    }

// DELETING==========================================
    const deleteuser = (userid) =>{

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
            axios.delete(`http://localhost:8081/deletUser/${userid}`)
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
    <button className="btn btn-secondary" onClick={addUser}>ADD NEW USER</button>
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
        {users && users.length > 0 ? (
            users.map((user) => (
                <tr key={user.id}>
                    <td>{user.id}</td>
                    <td>{user.name}</td>
                    <td>{user.email}</td>
                    <td>{user.role}</td>
                    <td><a href="#!" onClick={()=>deleteuser(user.id)} class="text-danger" data-mdb-tooltip-init title="Delete User"><i
                      class="fas fa-trash-alt"></i></a></td>
                </tr>
            ))):(
                <tr>
                  <td colSpan="4">No Members found.</td>
                </tr>
              )}
        </tbody>
    </table>
</div>


}