import axios from "axios";
import { useEffect, useState } from "react";

export function Dashbord(){
    const [userCount, setUserCount] = useState(0);
    const [taskCount, setTaskCount] = useState(0);

    useEffect(() => {
        axios.get('http://localhost:8081/users')
            .then(response => {
            setUserCount(response.data.length); // Get the count of users
            })
            .catch(error => {
            console.error('Error fetching users:', error);
            });

            axios.get('http://localhost:8081/tasks')
            .then(response => {
            setTaskCount(response.data.length); // Get the count of users
            })
            .catch(error => {
            console.error('Error fetching users:', error);
            });
    }, []);

    return <>
    <div className="row">
    
                <div className="card col-4 m-3 shadow border-0" style={{ maxWidth: "18rem", backgroundColor: "#e3f2fd" }}>
                    <div className="card-header bg-primary text-white">Users</div>
                    <div className="card-body">
                        <h5 className="card-title text-primary">Total Users</h5>
                        <p className="card-text display-4 text-center text-primary">{userCount}</p>
                    </div>
                </div>

                <div className="card col-4 m-3 shadow border-0" style={{ maxWidth: "18rem", backgroundColor: "#e3f2fd" }}>
                    <div className="card-header bg-primary text-white">Tasks</div>
                    <div className="card-body">
                        <h5 className="card-title text-primary">Total Tasks</h5>
                        <p className="card-text display-4 text-center text-primary">{taskCount}</p>
                    </div>
                </div>

    </div>

    
    </>
    
}