import axios from "axios";
import { useContext, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { MemberContext } from "./Navadmin";

export function AddUser(){

    const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  // const [pass, setPass] = useState("");
  // const [role, setRole] = useState(""); // Should be a valid enum string
  const { teamId, userid } = useContext(MemberContext);
    const nav = useNavigate()

    // useEffect(()=>{

    //     axios.get('http://localhost:8080/users')
    //     .then(response => {
    //         setUsers(response.data); // Store data in state
    //     })
    //     .catch(error => {
    //         console.error('Error fetching users:', error);
    //     });
    // })

// Generate Password======================================
    function generatePassword(length = 12) {
      const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*";
      let password = "";
      for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * charset.length);
        password += charset[randomIndex];
      }
      return password;
    }
    

    const handleSubmit = (e) => {
        e.preventDefault();
       const generatedPassword= generatePassword()
        
    
        const userData = {
          name,
          email,
          password:generatedPassword,
          role:"TEAM_MEMBER",
          teamId,
          enabled:false
        };
        console.log(JSON.stringify(userData))

        

        axios.post("http://localhost:8081/addUserByHead", userData)
            .then(() => {
                Swal.fire('Success!', 'Used added successfully and mail sent.', 'success');
                nav("/users")
            })
            .catch((err) => {
                console.error("Error:", err);
                alert("Failed to add Member.");
            });
  };
    return <>
    

  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-8 col-lg-6">
        <div class="card shadow-lg rounded-4">
          <div class="card-header bg-primary text-white text-center">
            <h4 class="mb-0">Add New User</h4>
          </div>
          <div class="card-body">
            <form>
              
              <div class="mb-3">
              <label>Name</label>
          <input type="text" className="form-control" value={name}
            onChange={(e) => setName(e.target.value)} required />
              </div>

              
              <div class="mb-3">
              <label>Email</label>
          <input type="email" className="form-control" value={email}
            onChange={(e) => setEmail(e.target.value)} required />
              </div>

             
              <div class="mb-3">
              <label>Role</label>
                <select className="form-select"  required>
                    <option value="TEAM_MEMBER">TEAM_MEMBER</option>
                    
                </select>
              </div>
              <div class="mb-3">
              <label>Team ID</label><br/>
                <label>{teamId}</label>
              </div>

    
              <div class="d-grid">
                <button type="submit" class="btn btn-primary" onClick={handleSubmit}>Add User</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>


    
    
    </>
}