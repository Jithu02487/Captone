import logo from './logo.svg';
import './App.css';
import { Nav } from './components/Nav';
import { Teams } from './components/Teams';
import { Users } from './components/Users';
import { BrowserRouter, Route, Router, Routes, useNavigate } from 'react-router-dom';
import { Tasks } from './components/Tasks';
import { Rooms } from './components/Rooms';
import { Meetings } from './components/Meetings';

function App() {


  return (
    <div className="App">
      
        <Routes>
          <Route path="/" element={<Nav />}>
            <Route path="users" element={<Users />} />
            <Route path="teams" element={<Teams />} />
            <Route path="tasks" element={<Tasks />} />
            <Route path="rooms" element={<Rooms />} />
            <Route path="meetings" element={<Meetings />} />
            {/* Add more nested routes here */}
          </Route>
        </Routes>
      
    </div>
  );
}

export default App;
