import axios from "axios";
import { useState } from "react";
import Swal from "sweetalert2";

export function ForgetPass() {
  const [email, setEmail] = useState("");

  const reset = (e) => {
    e.preventDefault();  // prevent form submission
    if (!email) {
      Swal.fire("Error", "Please enter your registered email.", "error");
      return;
    }

    console.log("Sending reset request...");
    axios
      .post(`http://localhost:8081/reset/${email}`)
      .then((response) => {
        console.log(response.data);
        Swal.fire(
          "Reset mail sent",
          "Check your email for the reset link.",
          "success"
        );
      })
      .catch((error) => {
        console.error(error);
        Swal.fire(
          "Error",
          "Something went wrong. Please try again.",
          "error"
        );
      });
  };

  return (
    <>
      <div className="wrapper">
        <div className="logo">
          <img
            src="https://icon-library.com/images/employees-icon-png/employees-icon-png-18.jpg"
            alt="logo"
          />
        </div>
        <div className="text-center mt-4 name">TaskPulse</div>
        <div className="text-center mt-2 mb-3"><h5>Reset Password</h5></div>
        <form className="p-3 mt-3">
          <div className="form-field d-flex align-items-center">
            <span className="far fa-user"></span>
            <input
              type="email"
              onChange={(e) => setEmail(e.target.value)}
              name="userName"
              id="userName"
              placeholder="Enter registered email"
              required
            />
          </div>

          <button type="button" onClick={reset} className="btn mt-3">
            Send Reset Link
          </button>
        </form>
        <div className="text-center fs-6">
          <a href="signin">Sign in</a>
        </div>
      </div>
    </>
  );
}
