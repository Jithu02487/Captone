import { useEffect, useState, useRef, useContext } from "react";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";
import axios from "axios";
import { MemberContext } from "./Navadmin";

export default function Chat() {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");
  const [connected, setConnected] = useState(false);
  const stompClientRef = useRef(null);
  const [username, setUsername] = useState("");
  const bottomRef = useRef(null);
  const { teamId, userid } = useContext(MemberContext);

  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: "smooth" });
    axios.get(`http://localhost:8080/users/${userid}`)
            .then(response => {
                setUsername(response.data.name)
            })
            .catch(error => {
                console.error('Error fetching users:', error);
            });

  }, [messages]);

  useEffect(() => {
    // Fetch chat history for the team
    axios.get(`http://localhost:8080/api/messages/team/${teamId}`)
      .then((res) => setMessages(res.data))
      .catch((err) => console.error("Error fetching messages", err));

    // Setup WebSocket + STOMP
    const stompClient = Stomp.over(() => new SockJS("http://localhost:8080/ws")); // ✅ This is the new correct way


    stompClient.connect({}, () => {
      setConnected(true);
      // stompClient.subscribe(`/topic/messages/team/${teamId}`, (msg) => {
      //   const newMessage = JSON.parse(msg.body);
      //   setMessages((prev) => [...prev, newMessage]);
      // });
      stompClient.subscribe(`/topic/messages/team/${teamId}`, (msg) => {
        const newMessage = JSON.parse(msg.body);
      
        setMessages((prev) => {
          const alreadyExists = prev.some(m => m.id === newMessage.id);
          if (alreadyExists) return prev;
          return [...prev, newMessage];
        });
      });
    }, (error) => {
      console.error("STOMP connection error:", error);
    });

    stompClientRef.current = stompClient;

    return () => {
      if (stompClientRef.current?.connected) {
        stompClientRef.current.disconnect();
      }
    };
  }, [teamId]);

  const sendMessage = () => {
    if (!connected || !stompClientRef.current?.connected) {
      console.warn("Not connected yet");
      return;
    }

    if (input.trim()) {
      const msg = {
        sender: username,
        content: input,
      };
      stompClientRef.current.send(`/app/chat/team/${teamId}`, {}, JSON.stringify(msg));
      setInput("");
    }
    // window.location.reload()
  };

  return (
    
    <div className="chat">
      <div className="container justify-content-center">
        <div className="card mt-5">
          <div className="d-flex flex-row justify-content-between p-3 adiv text-white bg-primary">
            <i></i>
            <span className="pb-3">Team Chat</span>
            <i ></i>
          </div>

          <div className="flex-row p-3">
            <div className="messages" style={{ maxHeight: "350px", overflowY: "auto" }}>
            {messages.map((msg, i) => {
  const isMine = msg.sender === username;

  return (
    <div
      key={i}
      style={{
        display: "flex",
        justifyContent: isMine ? "flex-end" : "flex-start",
        marginBottom: "10px",
      }}
    >
      {!isMine && (
        <img
          src="https://img.icons8.com/color/48/000000/circled-user-female-skin-type-7.png"
          width="30"
          height="30"
          style={{ marginRight: "10px" }}
          alt="avatar"
        />
      )}
      <div
        className="chat"
        style={{
          backgroundColor: isMine ? "#d1e7dd" : "#f1f0f0",
          borderRadius: "10px",
          padding: "10px",
          maxWidth: "70%",
          textAlign: isMine ? "right" : "left",
        }}
      >
        <strong>{msg.sender}</strong>: {msg.content}
      </div>
      {isMine && (
        <img
          src="https://img.icons8.com/color/48/000000/user.png"
          width="30"
          height="30"
          style={{ marginLeft: "10px" }}
          alt="avatar"
        />
      )}
      <div ref={bottomRef} />

    </div>
  );
})}

            </div>
          </div>

          <div className="form-group px-3 mb-3">
            <textarea
              value={input}
              onChange={(e) => setInput(e.target.value)}
              className="form-control"
              rows="3"
              placeholder="Type your message"
            ></textarea>
            <button className="btn btn-primary mt-2" onClick={sendMessage} disabled={!connected}>Send</button>
          </div>
        </div>
      </div>
    </div>
  );
}
