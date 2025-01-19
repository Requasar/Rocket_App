import React, { useState } from "react";
import { FormControl, Input, Button, InputLabel, FormHelperText, CssBaseline } from "@mui/material";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import axios from 'axios';  // axios'ı import ettik

function Login() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');

  const handleUsername = (value) => {
    setUsername(value)
  }

  const handlePassword = (value) => {
    setPassword(value)
  }

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8080/auth/login', {
        userName: username,
        password: password,
      });

      // Check for JWT token in response
      if (response.data && response.data.token) {
        setMessage('Login successful!');
        localStorage.setItem('token', response.data.token); // Save token in localStorage
        localStorage.setItem('currentUser', response.data.userId);  // Optionally save userId
        localStorage.setItem('userName', username); // Optionally save username

        // Optionally, redirect to a protected route or show a success message
        // window.location.href = '/home';  // Example redirect
      } else {
        setMessage('Login successful, but no token returned.');
      }
    } catch (error) {
      setError('Login failed. Please check your username and password.');
    }
  }

  const handleSkip = () => {
    // You can implement a skip functionality if necessary
    console.log("Skip login");
  }

  const darkTheme = createTheme({
    palette: {
      mode: "dark", 
    },
  });

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <FormControl
             style={{
              width: "300px",
              margin: "20px auto",
              padding: "20px",
              background: "#424242", 
              borderRadius: "10px",
            }}
          >

        <InputLabel style={{top:20}}>Username</InputLabel>
        <Input style={{ marginTop: 10 }} value={username} onChange={(e) => setUsername(e.target.value)} />
        <InputLabel style={{ top: 70 }}>Password</InputLabel>
        <Input style={{ top: 40 }} value={password} onChange={(e) => setPassword(e.target.value)} type="password" />
        
        <Button
          variant="contained"
          style={{
            marginTop: 25,
            background: "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)",
            color: "white",
          }} onClick={handleLogin}>Login</Button>

        {error && <p style={{ color: 'red' }}>{error}</p>}
        {message && <p style={{ color: 'green' }}>{message}</p>}

        <FormHelperText style={{marginTop: 20}}>Don't you have an admin account?</FormHelperText>
        <Button variant="contained" 
        style={{marginTop: 10, 
        background: "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)", 
        color: "white"}} onClick={handleSkip}>Skip</Button>
      </FormControl>
    </ThemeProvider>
  );
}

export default Login;
