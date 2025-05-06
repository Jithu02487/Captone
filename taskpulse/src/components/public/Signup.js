import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';

export function Signup() {
  const [selectedRole, setSelectedRole] = useState('');
  const [teamCreated, setTeamCreated] = useState(false);
  const [teamName, setTeamName] = useState("");
  const [teamId, setTeamId] = useState("");
  const [teamDescription, setTeamDescription] = useState("");
  const [userNmae, setUserName] = useState("");
  const [userRole, setUserRole] = useState("");
  const [UserEmail, setUserEmail] = useState("");
  const [password, setPassword] = useState("");
  // const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [confirmPasswordError, setconfirmPasswordError] = useState("");
  const nav = useNavigate();

  const handleRoleChange = (event) => {
    setSelectedRole(event.target.value);
  };

  const passwordHandle = (e) => {
    const pass = e.target.value; // Get the current value of the input
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    
    if (!pass) {
        setPasswordError("Password is required");
    } else if (!passwordRegex.test(pass)) {
        setPasswordError('Password must be at least 8 characters, include a number and a special character');
    } else {
        setPasswordError('');
        setPassword(pass)
    }
};

  const confirmPasswordHandle = (e)=>{
    
    if(e.target.value!==password){
        setconfirmPasswordError("Password must be same")
    }else{
        setconfirmPasswordError('')
    }
  }

  const createHead = ()=>{
    if (!userNmae || !UserEmail || !password || !teamId) {
        console.error("All fields are required");
        return;
    }
    const user = {
        "name":userNmae,
        "email":UserEmail,
        "password": password,
        "role":userRole,
        "teamId":teamId,
        "enabled":false
    }

    axios.post("http://localhost:8081/addUser",user)
    .then(response=>{
        console.log(response.data);
        Swal.fire("Verification mail sent", response.data, "success")
        nav("/signin")
    })
    .catch(error=>{
        console.error("TeamHead creation failed " + error)
    })
  };

  const handleTeamCreation = () => {
    const team = 
        {
            "name":teamName,
            "description":teamDescription
        }
    axios.post("http://localhost:8081/addTeam",team)
    .then(response=>{
        setTeamId(response.data.id)
        setUserRole("TEAM_HEAD")

        setTeamCreated(true);
    })
    .catch(error=>{
        console.error("error wile creating team"+error);
    })
  };

  return (
    <>
      <div className="container min-vh-100 d-flex justify-content-center align-items-center">
  <div className="card shadow-lg p-4" style={{ maxWidth: '500px', width: '100%' }}>
    <div className="card-header text-center bg-primary text-white">
      <h4 className="mb-0">Signup</h4>
    </div>
    <div className="card-body">

      {/* Dropdown for selecting Team Head or Team Member */}
      <div className="mb-3">
        <label htmlFor="role" className="form-label">Select Role</label>
        <select
          id="role"
          className="form-select"
          value={selectedRole}
          onChange={handleRoleChange}
          required
        >
          <option value="">Choose a role</option>
          <option value="team-head">Team Head</option>
          <option value="team-member">Team Member</option>
        </select>
      </div>

      {/* Team Creation Form */}
      {selectedRole === 'team-head' && !teamCreated && (
        <div>
          <h5 className="text-center mb-3">Create a Team</h5>
          <div className="mb-3">
            <label htmlFor="teamName" className="form-label">Team Name</label>
            <input
              type="text"
              className="form-control"
              id="teamName"
              placeholder="Enter team name"
              required
              onChange={(e) => setTeamName(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="teamDescription" className="form-label">Team Description</label>
            <input
              type="text"
              className="form-control"
              id="teamDescription"
              placeholder="Enter team description"
              required
              onChange={(e) => setTeamDescription(e.target.value)}
            />
          </div>
          <button
            type="button"
            className="btn btn-success w-100"
            onClick={handleTeamCreation}
          >
            Create Team
          </button>
        </div>
      )}

      {/* User Creation (Signup Form) */}
      {((selectedRole === 'team-head' && teamCreated) || selectedRole === 'team-member') && (
        <form className="mt-4">
          <h5 className="text-center mb-3">Create User</h5>
          {teamId && (
            <div className="mb-3 text-center">
              <span className="badge bg-secondary">Team ID: {teamId}</span>
            </div>
          )}
          <div className="mb-3">
            <label htmlFor="name" className="form-label">Full Name</label>
            <input
              type="text"
              className="form-control"
              onChange={(e) => setUserName(e.target.value)}
              id="name"
              placeholder="Enter your full name"
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email address</label>
            <input
              type="email"
              onChange={(e) => setUserEmail(e.target.value)}
              className="form-control"
              id="email"
              placeholder="Enter your email"
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input
              type="password"
              onChange={passwordHandle}
              className="form-control"
              id="password"
              placeholder="Enter your password"
              required
            />
            {passwordError && (
              <small className="text-danger">{passwordError}</small>
            )}
          </div>
          <div className="mb-3">
            <label htmlFor="confirmPassword" className="form-label">Confirm Password</label>
            <input
              type="password"
              onChange={confirmPasswordHandle}
              className="form-control"
              id="confirmPassword"
              placeholder="Confirm your password"
              required
            />
            {confirmPasswordError && (
              <small className="text-danger">{confirmPasswordError}</small>
            )}
          </div>
          <button
            type="button"
            onClick={createHead}
            className="btn btn-primary w-100"
          >
            Sign Up
          </button>
        </form>
      )}

    </div>
  </div>
</div>

    </>
  );
}
