import axios from "axios";
import { useEffect } from "react";
import Swal from "sweetalert2";

export function VerifyToken(){


    useEffect(() => {
        const token = new URLSearchParams(window.location.search).get('token');
        axios.get(`http://localhost:8080/verify?token=${token}`)
          .then(res => Swal.fire("Success", res.data, "success"))
          .catch(err => Swal.fire("Error", "Verification failed.", "error"));
      }, []);
      
}