import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import ListRocketComponent from './components/ListRocketComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import { BrowserRouter, Route, Routes} from 'react-router-dom';
import RocketComponent from './components/RocketComponent';
import Login from './components/Login';
import LoginForm from './components/LoginForm';



function App() {
  return (
    <>
    <BrowserRouter>
      <HeaderComponent /> {/* Those bottom 3 are constant we should encapsule them with browserRouter*/}
        <Routes> {/*container are a parent for all the integer in routes*/}
           {/* Login  */}
           <Route path='/auth' element={<Login />} />
            {/* http://localhost:3000/ */}
            {/*<Route path='/' element={<ListRocketComponent/>}></Route>*/}
            {/* http://localhost:3000/rocket */} {/* At this adress ListRocket Component will work or activated with path we make our adress after main adress*/}
            <Route path='/rocket' element={<ListRocketComponent/>}></Route>
            {/* http://localhost:3000/rocket/add-rocket */}
            <Route path='/rocket/add-rocket' element={<RocketComponent/>}></Route> {/* at that adress RocketComponent will be rendered */}
            {/* http://localhost:3000/rocket/update-rocket */}
            <Route path='/rocket/update-rocket/:id' element={<RocketComponent/>}></Route> {/* at that adress RocketComponent will be rendered */}
        </Routes>
        {/* <ListRocketComponent/>*/}
    </BrowserRouter>
    </>
  )
}

export default App;