import { useState } from 'react';
import './App.css'
import Login from './components/Login';

function App() {
  const [logged, setLogged] = useState(false);
  
  return (
    <>
      {!logged ?
        <Login setLogged={setLogged}/>
      : 
      <div>
        
      </div>}
    </>
  )
}

export default App
