import { useEffect, useState } from "react"
import { Link, Outlet, useNavigate } from "react-router-dom";
import { Sidebar } from "./Sidebar";
import React, { createContext } from "react";
import axios from "axios";
export const MemberContext = createContext();

export function Navmember({ teamId, userid }){

    const loggedIn = localStorage.getItem("status")
    const [logged,setLogged]=useState(false);
    const nav = useNavigate();
    const [username, setUsername] = useState("");

    useEffect(()=>{
      axios.get(`http://localhost:8080/users/${userid}`)
      .then(response => {
          setUsername(response.data.name)
      })
      .catch(error => {
          console.error('Error fetching users:', error);
      });
    })

    return  (
      <MemberContext.Provider value={{ teamId, userid }}>
        <div className="container-fluid">
    <div className="row flex-nowrap">
      {/* Sidebar */}
      <div className="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
        <Sidebar />
  
      </div>

      {/* Main content with top nav and outlet */}
      <div className="col py-3">
                    <nav className="navbar navbar-dark bg-dark mb-4 sticky-top">
                <div className="container-fluid">
                  <Link className="navbar-brand" to="#">TaskPulse</Link>

                  {/* Centered Link */}
                  <div className="mx-auto">
                    <Link className="nav-link text-white" to="#">Team Member</Link>
                  </div>

                  {/* Right-aligned Links */}
                  <div className="d-flex align-items-center gap-2">
                    <span className="nav-link text-white">{username}</span>
                    <img
                      src="https://img.icons8.com/color/48/000000/user.png"
                      alt="avatar"
                      style={{ width: "30px", height: "30px", borderRadius: "50%" }}
                    />
                  </div>
                </div>
              </nav>


        {/* Render current page here */}
        <Outlet />
      </div>
    </div>
  </div>
      </MemberContext.Provider>
    );

    
}