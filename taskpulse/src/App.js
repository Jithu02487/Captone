import logo from './logo.svg';
import './App.css';
import { Teams } from './components/Teams';
import { Users } from './components/Users';
import { BrowserRouter, Route, Router, Routes, useNavigate } from 'react-router-dom';
import { Tasks } from './components/Tasks';
import { Rooms } from './components/Rooms';
import { Meetings } from './components/Meetings';
import Chat from './components/Chat';
import { AddTask } from './components/AddTask';
import { AddUser } from './components/AddUser';
import { VerifyToken } from './components/VerifyToken';
import { Navadmin } from './components/Navadmin';
import { Navmember } from './components/team_member/Navmember';
import { Tasksmember } from './components/team_member/Tasks';
import { Usersmember } from './components/team_member/Usersmember';
import Chatmember from './components/team_member/Chatmember';
import { Dashbord } from './components/admin/Dashbord';

function App() {


  return (
    <div className="App">
      
        <Routes>
          <Route path="/" element={<Navadmin teamId="34" userid="33"/>}>
            <Route path="users" element={<Users />} />
            <Route path="teams" element={<Teams />} />
            <Route path="tasks" element={<Tasks />} />
            <Route path="rooms" element={<Rooms />} />
            <Route path="meetings" element={<Meetings />} />
            <Route path="addtask" element={<AddTask />} />
            <Route path="adduser" element={<AddUser />} />
            <Route path="home" element={<Dashbord />} />
            <Route path="verify" element={<VerifyToken />} />
            <Route path="chats" element={<Chat />} />
            {/* Add more nested routes here */}
          </Route>
          <Route path="/member" element={<Navmember teamId="34" userid="65"/>}>
            <Route path="tasks" element={<Tasksmember />}/>
            <Route path="users" element={<Usersmember />}/>
            <Route path="chats" element={<Chatmember />}/>
    
            {/* <Route path="chats" element={<Chat teamId="34" username="manu" />} /> */}
        </Route>
        </Routes>
      
    </div>
  );
}

export default App;
