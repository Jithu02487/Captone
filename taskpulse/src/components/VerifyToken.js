import axios from "axios";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

export function VerifyToken(){

    const nav = useNavigate();


    useEffect(() => {
        const token = new URLSearchParams(window.location.search).get('token');
        axios.get(`http://localhost:8081/verify`, { params: { token } })
          .then(res => {Swal.fire("Success", res.data, "success");
          axios
          .delete("http://localhost:8081/deleteToken", { params: { token } })
          .then(() => {
            console.log("Token deleted successfully");
          })
          .catch(err => {
            console.error("Token deletion failed:", err.response?.data);
          });
          nav("/signin")
        })
          .catch(err => Swal.fire("Error", "Verification failed.", "error"));
      }, []);
      
}