import axios from "axios";
import { useEffect, useState } from "react";

export function Rooms(){



    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/rooms')
            .then(response => {
                setRooms(response.data); // Store data in state
            })
            .catch(error => {
                console.error('Error fetching Rooms:', error);
            });
    }, []);


    return <div>
        <button className="btn btn-secondary">ADD NEW ROOM</button>
        <hr/>
    <h2>Rooms</h2>
    <table className="table table-striped">
        <thead>
            <tr>
                <th scope="col">NAME</th>
                <th scope="col">TOPIC</th>
                <th scope="col">CREATED_AT</th>
            </tr>
        </thead>
        <tbody>
        {rooms && rooms.length > 0 ? (
  rooms.map((room) => (
    <tr key={room.id}>
      <td>{room.name}</td>
      <td>{room.topic}</td>
      <td>{room.createdAt}</td>
    </tr>
  ))
) : (
  <tr>
    <td colSpan="4">No Rooms found.</td>
  </tr>
)}
        </tbody>
    </table>
</div>
}