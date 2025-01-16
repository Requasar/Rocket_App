import React, {useState} from "react";
import { FormControl, Input, Button, InputLabel, TextField, FormHelperText, CssBaseline } from "@mui/material";
import { ThemeProvider, createTheme } from "@mui/material/styles";

function LoginForm() {

  const[username, setUsername] = useState("");
  const[password, setPassword] = useState("");

  const handleUsername = (value) => {
    setUsername(value)
  }

  const handlePassword = (value) => {
    setPassword(value)
  }

  const sendRequest = (path) => {
    fetch("/auth/" + path, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: username,
        password :password,
      }),
    })
    .then((response) => response.json())
    .then((result) => {localStorage.setItem("tokenKey", result.message);
                    localStorage.setItem("currentUser", result.userId);
                    localStorage.setItem("userName",username)})
    .catch((err) => console.log(err))
  
  }



  const handleLogin = () => {
    sendRequest("login")
    setUsername("")
    setPassword("")
  }

  const handleSkip = () => {
    sendRequest("skip")
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
        <Input style={{ marginTop: 10 }} onChange={(e) => setUsername(e.target.value)}/>
        <InputLabel style={{ top: 70 }}>Password</InputLabel>
        <Input style={{ Top: 40 }} 
        onChange={(e) => setPassword(e.target.value)}/>
        
        <Button
          variant="contained"
          style={{
            marginTop: 25,
            background: "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)",
            color: "white",
          }}onClick={handleLogin}>Login</Button>

        <FormHelperText style={{marginTop: 20}}>Don't you have an admin account?</FormHelperText>
        <Button variant="contained" 
        style={{marginTop: 10, 
        background: "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)", 
        color: "white"}} onClick={handleSkip}>Skip</Button>
      </FormControl>
    </ThemeProvider>
  );
}

export default LoginForm;
