import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

export function Reset(){

    const nav = useNavigate();
    const [token,setToken]=useState();
    const [password, setPassword] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [confirmPasswordError, setconfirmPasswordError] = useState("");


  useEffect(() => {
    const tokn = new URLSearchParams(window.location.search).get('token');
    if(tokn){

        setToken(tokn)

    }else{
        console.log("Token not found")
        Swal.fire("Success", "Token not found", "error")
        nav("/reset");
        
    }
  }, []);


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

  const resetpass = () => {
    axios.post(`http://localhost:8081/passReset/${token}`, {
        newpass: password
    })
    .then(result => {
        // After password reset, delete the token
        axios
          .delete("http://localhost:8081/deleteToken", { params: { token } })
          .then(() => {
            console.log("Token deleted successfully");
            Swal.fire("Success", result.data, "success")
            nav("/signin")
          })
          .catch(err => {
            console.error("Token deletion failed:", err.response?.data);
          });
       
    })
    .catch(error => {
        console.error("Error in password reset: ", error);
    });
};




    

      return <>


      <div class="wrapper " >
              <div class="logo">
                  <img src="https://icon-library.com/images/employees-icon-png/employees-icon-png-18.jpg" alt="logo"/>
              </div>
              <div class="text-center mt-4 name">
                  TaskPulse
              </div>
              Reset password
              <form class="p-3 mt-3">
                  <div class="form-field d-flex align-items-center">
                      <span class="far fa-user"></span>
                      <input type="password" onChange={passwordHandle} name="newpassword" id="newpassword" placeholder="New Password"/>
                  </div>
                      {passwordError && <small className="text-danger">{passwordError}</small>}
                  <div class="form-field d-flex align-items-center">
                      <span class="fas fa-key"></span>
                      <input type="password" onChange={confirmPasswordHandle} name="confirmpassword" id="confirmpassword" placeholder="Password"/>
                  </div>
                      {confirmPasswordError && <small className="text-danger">{confirmPasswordError}</small>}
                  <button type="button" className="btn mt-3" onClick={resetpass}>Reset</button>
              </form>
              <div class="text-center fs-6">
              </div>
          </div>
          
          
          
          </>


      
}