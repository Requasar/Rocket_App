import React, { useState } from "react";

function Login() {
  const [username, setUsername] = useState(""); 
  const [password, setPassword] = useState(""); 
  const [error, setError] = useState(null); 

  const handleSubmit = async (e) => {
    e.preventDefault(); 
    setError(null); 

    try {
      const response = await fetch("http://localhost:8080/api/user/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }), 
        credentials: "include", 
      });

      if (!response.ok) {
        throw new Error("Giriş başarısız! Lütfen tekrar deneyin.");
      }

      const data = await response.text(); 
      alert(data); 


      window.location.href = "/api/rocket";
    } catch (error) {
      setError(error.message); 
    }
  };

  return (
    <div style={{ margin: "20px" }}>
      <h2>Login</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
