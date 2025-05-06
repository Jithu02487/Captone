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
import { Signup } from './components/public/Signup';
import { SignIn } from './components/public/SignIn';
import { ForgetPass } from './components/public/FogetPass';
import { Reset } from './components/public/Reset';

function App() {


  return (
    <div className="App">
      
      
        <Routes>
      {/* PUBLIC */}
      <Route path='/signup' element={<Signup/>}/>
      <Route path='/verify' element={<VerifyToken/>}/>
      <Route path='/signin' element={<SignIn/>}/>
      <Route path='/resetpass' element={<ForgetPass/>}/>
      <Route path='/reset' element={<Reset/>}/>

      {/* ADMIN */}
          <Route path="/" element={<Navadmin teamId="1" userid="289"/>}>
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


          {/* MEMBER */}
          </Route>
          <Route path="/member" element={<Navmember teamId="1" userid="4"/>}>
            <Route path="tasks" element={<Tasksmember />}/>
            <Route path="users" element={<Usersmember />}/>
            <Route path="chats" element={<Chatmember />}/>
        </Route>
        </Routes>
      
    </div>
  );
}

export default App;
