import { useEffect, useState } from "react"
import { Link, Outlet, useNavigate } from "react-router-dom";
import { Users } from "./Users";
import { Sidebar } from "./Sidebar";

export function Nav(){

    const loggedIn = localStorage.getItem("status")
    const [logged,setLogged]=useState(false);
    const nav = useNavigate();

    // useEffect(()=>{
    //     if(loggedIn!=null){
    //         setLogged(true)
    //     }else{
    //         setLogged(false)
    //     }
    // })

    return  <div className="container-fluid">
    <div className="row flex-nowrap">
      {/* Sidebar */}
      <div className="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
        <Sidebar />
      </div>

      {/* Main content with top nav and outlet */}
      <div className="col py-3">
        <nav className="navbar navbar-dark bg-dark mb-4">
          <div className="container-fluid">
            <Link className="navbar-brand" to="/">TaskPulse</Link>
            <div className="d-flex">
              <Link className="nav-link text-white" to="/">Home</Link>
              <Link className="nav-link text-white" to="/users">Users</Link>
              <Link className="nav-link text-white" to="/teams">Teams</Link>
            </div>
          </div>
        </nav>

        {/* Render current page here */}
        <Outlet />
      </div>
    </div>
  </div>

    
}