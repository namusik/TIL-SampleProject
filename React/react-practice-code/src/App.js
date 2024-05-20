import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';

function App() {

  const [state, setState] = useState({
    a:1,
    b:2
  });

  // 상태를 업데이트할 때 기존 상태를 유지하지 않으면 `b` 필드가 사라짐
  setState({ a: 3 , c:4});
  console.log(state); // { a: 3 }, `b` 필드는 사라짐  

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
