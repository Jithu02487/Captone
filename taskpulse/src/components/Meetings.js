import axios from "axios";
import { useEffect, useState } from "react";

export function Meetings(){



    const [meetings, setMeetings] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8081/meetings')
            .then(response => {
                setMeetings(response.data); // Store data in state
            })
            .catch(error => {
                console.error('Error fetching Meetings:', error);
            });
    }, []);


    return <div>
        <button className="btn btn-secondary">CREATE NEW MEETING</button>
        <hr/>
    <h2>MEETINGS</h2>
    <table className="table table-striped">
        <thead>
            <tr>
                <th scope="col">TOPIC</th>
                <th scope="col">TEAM_NAME</th>
                <th scope="col">STARTED_AT</th>
            </tr>
        </thead>
        <tbody>
        {meetings && meetings.length > 0 ? (
  meetings.map((meeting) => (
    <tr key={meeting.id}>
      <td>{meeting.topic}</td>
      <td>{meeting.team && meeting.team.name ? meeting.team.name : "No Team found"}</td>
      <td>{meeting.startedAt}</td>
    </tr>
  ))
) : (
  <tr>
    <td colSpan="4">No Meetings found.</td>
  </tr>
)}
        </tbody>
    </table>
</div>
}