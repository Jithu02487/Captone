import axios from "axios";
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

export function AddTask(){

    const [users, setUsers] = useState([]);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [status, setStatus] = useState("PENDING");
    const [dueDate, setDueDate] = useState("");
    const [userId, setUserId] = useState("");
    const nav = useNavigate()

    useEffect(()=>{

        axios.get('http://localhost:8081/users')
        .then(response => {
            setUsers(response.data); // Store data in state
        })
        .catch(error => {
            console.error('Error fetching users:', error);
        });
    })

    const handleSubmit = (e) => {
        e.preventDefault();
    
        const taskData = {
          title,
          description,
          status,
          dueDate,
          userId,
        };

        axios.post("http://localhost:8081/addTask", taskData)
            .then((res) => {
                Swal.fire('Success!', 'Task added successfully.', 'success');
                nav("/tasks")
            })
            .catch((err) => {
                console.error("Error:", err);
                alert("Failed to add task.");
            });
  };
    return <>
    

  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-8 col-lg-6">
        <div class="card shadow-lg rounded-4">
          <div class="card-header bg-primary text-white text-center">
            <h4 class="mb-0">Add New Task</h4>
          </div>
          <div class="card-body">
            <form>
              
              <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" id="title" class="form-control" placeholder="Enter task title" required onChange={(e)=>setTitle(e.target.value)}/>
              </div>

              
              <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea onChange={(e)=>setDescription(e.target.value)}id="description" class="form-control" rows="4" placeholder="Describe the task..." required></textarea>
              </div>

             
              <div class="mb-3">
                <label for="status" class="form-label">Status</label>
                <select onChange={(e)=>setStatus(e.target.value)} id="status" class="form-select" required>
                  <option selected disabled>Select status</option>
                  <option value="TODO">To Do</option>
                  <option value="IN_PROGRESS">In Progress</option>
                  <option value="DONE">Done</option>
                </select>
              </div>
              <div class="mb-3">
                <label for="status" class="form-label">User</label>
                <select onChange={(e)=>setUserId(e.target.value)} id="status" class="form-select" required>
                  <option selected disabled>Select user</option>
                  {users.map((user) => (
                  <option value={user.id}>{user.name}</option>
            ))}
                </select>
              </div>

              
              <div class="mb-3">
                <label for="createdDate" class="form-label">Due Date</label>
                <input onChange={(e)=>setDueDate(e.target.value)}type="date" id="createdDate" class="form-control" required />
              </div>

    
              <div class="d-grid">
                <button type="submit" class="btn btn-primary" onClick={handleSubmit}>Add Task</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>


    
    
    </>
}