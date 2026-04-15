import React, { useState } from "react";
import "./App.css";

function App() {
  const [userId, setUserId] = useState("");
  const [queueId, setQueueId] = useState("");
  const [tokenId, setTokenId] = useState(null);
  const [tokenNumber, setTokenNumber] = useState("");
  const [position, setPosition] = useState("");
  const [dashboard, setDashboard] = useState("");

  // JOIN QUEUE
  const joinQueue = async () => {
    try {
      const res = await fetch(
        `http://localhost:8080/queue/join?userId=${userId}&queueId=${queueId}`,
        { method: "POST" }
      );

      const data = await res.json();

      setTokenId(data.id);
      setTokenNumber(data.tokenNumber);
    } catch (error) {
      console.error(error);
    }
  };

  // GET POSITION
  const getPosition = async () => {
    const res = await fetch(
      `http://localhost:8080/queue/position?tokenId=${tokenId}`
    );

    const text = await res.text();
    setPosition(text);
  };

  // DASHBOARD
  const getDashboard = async () => {
    const res = await fetch(
      `http://localhost:8080/queue/dashboard?queueId=${queueId}`
    );

    const text = await res.text();
    setDashboard(text);
  };

  // NEXT TOKEN
  const nextToken = async () => {
    await fetch(
      `http://localhost:8080/queue/next?queueId=${queueId}`,
      { method: "POST" }
    );

    alert("Queue moved to next token!");
    getDashboard();
  };

  return (
    <div className="container">
      <h1>Smart Queue System</h1>

      <input
        type="text"
        placeholder="Enter User ID"
        value={userId}
        onChange={(e) => setUserId(e.target.value)}
      />

      <input
        type="text"
        placeholder="Enter Queue ID"
        value={queueId}
        onChange={(e) => setQueueId(e.target.value)}
      />

      <br />

      <button onClick={joinQueue}>Join Queue</button>

      <p className="token">Token Number: {tokenNumber}</p>

      <button onClick={getPosition}>Check Position</button>
      <p className="info">{position}</p>

      <button onClick={getDashboard}>View Dashboard</button>
      <p className="info">{dashboard}</p>

      <button onClick={nextToken}>Next Token (Admin)</button>
    </div>
  );
}

export default App;