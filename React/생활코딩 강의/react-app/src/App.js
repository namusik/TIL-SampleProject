import logo from './logo.svg';
import './App.css';
import {useState} from 'react';


function Header(props){
  console.log('props', props, props.title);
  return <header>
    <h1><a href="/" onClick={(event)=>{
      event.preventDefault();
      props.onChangeMode();
    }} >{props.title}</a></h1>
  </header>
}
function Nav(props){
  const LIS = []
  for(let i=0; i<props.topics.length; i++){
    let t = props.topics[i];
    LIS.push(<li key={t.id}>
        <a id={t.id} href={'/read/'+t.id} onClick={(event)=>{
          event.preventDefault();
          props.onChangeMode(event.target.id);
        }}>{t.title}</a>
      </li>)
  }
  return <nav>
    <ol>
      {LIS}
    </ol>
  </nav>
}
function Article(props){
  console.log('props', props);
  return <article>
    <h2>{props.title}</h2>
    {props.body}
  </article>
}

function App() {;
  // const _mode = useState('WELCOME');
  // const mode = _mode[0];
  // const setMode = _mode[1];
  const [mode, setMode] = useState('WELCOME');
  // console.log('_mode', _mode);
  const TOPICS = [
    {id:1, title:'html', body:'html is ...'},
    {id:2, title:'css', body:'css is ....'},
    {id:3, title:'javascript', body:'js is ....'}
  ]
  let content = null;
  if(mode === 'WELCOME'){
    content = <Article title="Welcomee" body="Hello, WEB"></Article>
  }else if(mode === 'READ'){
    content = <Article title="Read" body="Hello, Read"></Article>
  }
  return (
    <div>
      <Header title="REACT" onChangeMode={()=>{
        setMode('WELCOME');
      }}></Header>
      <Nav topics={TOPICS} onChangeMode={(id)=>{
        setMode('READ');
      }}></Nav>
      {content}
    </div>  
  );
}

export default App;
