import React, { useState } from "react";
import { TextField, Button, FormHelperText, CssBaseline } from "@mui/material";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import axios from 'axios';

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');

  const handleUsername = (value) => {
    setUsername(value);
  };

  const handlePassword = (value) => {
    setPassword(value);
  };

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8080/auth/login', {
        userName: username,
        password: password,
      });

      if (response.data && response.data.token) {
        setMessage('Login successful!');
        localStorage.setItem('token', response.data.token); 
        localStorage.setItem('currentUser', response.data.userId);  
        localStorage.setItem('userName', username); 
        localStorage.setItem('role', response.data.role); 

        const userRole = response.data.role;
        if (userRole === "USER") {
          window.location.href = 'http://localhost:3000/rocket'; 
        } else if (userRole === "ADMIN") {
          window.location.href = 'http://localhost:3000/admin'; 
        }
      } else {
        setMessage('Login successful, but no token returned.');
      }
    } catch (error) {
      setError('Login failed. Please check your username and password.');
    }
  };

  const handleSkip = () => {
    console.log("Skip login");
  };

  const darkTheme = createTheme({
    palette: {
      mode: "dark", 
    },
  });

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <div
        style={{
          width: "300px",
          margin: "20px auto",
          padding: "20px",
          background: "#424242", 
          borderRadius: "10px",
        }}
      >
        <TextField
          label="Username"
          variant="filled"
          fullWidth
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          style={{ marginBottom: 10 }}
        />

        <TextField
          label="Password"
          variant="filled"
          type="password"
          fullWidth
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={{ marginBottom: 20 }}
        />

        <Button
          variant="contained"
          style={{
            marginTop: 25,
            background: "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)",
            color: "white",
          }} 
          onClick={handleLogin}
        >
          Login
        </Button>

        {error && <p style={{ color: 'red' }}>{error}</p>}
        {message && <p style={{ color: 'green' }}>{message}</p>}

        <FormHelperText style={{marginTop: 20}}>Don't you have an admin account?</FormHelperText>
        <Button
          variant="contained" 
          style={{
            marginTop: 10, 
            background: "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)", 
            color: "white"
          }} 
          onClick={handleSkip}
        >
          Skip
        </Button>
      </div>
    </ThemeProvider>
  );
}

export default Login;
