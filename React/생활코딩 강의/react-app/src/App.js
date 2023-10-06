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
          props.onChangeMode(Number(event.target.id));
          //문자열을 숫자로 바꾸기 위해.
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

function App() {
  // const _mode = useState('WELCOME');
  // const mode = _mode[0];
  // const setMode = _mode[1];
  const [mode, setMode] = useState('WELCOME');
  // console.log('_mode', _mode);

  const [id, setId] = useState(null); //현재 값이 선택되지 않았으니까 초기값 null

  const TOPICS = [
    {id:1, title:'html', body:'html is ...'},
    {id:2, title:'css', body:'css is ....'},
    {id:3, title:'javascript', body:'js is ....'}
  ]

  let content = null;
  if(mode === 'WELCOME'){
    content = <Article title="Welcomee" body="Hello, WEB"></Article>
  }else if(mode === 'READ'){
    let title, body = null; //값 미리 초기화
    for(let i=0; i<TOPICS.length; i++){
      console.log(TOPICS[i].id, id);
      if(TOPICS[i].id === id){
        title = TOPICS[i].title;
        body = TOPICS[i].body;  
      }
    }
    content = <Article title={title} body={body}></Article>
  }

  return (
    <div>
      <Header title="REACT" onChangeMode={()=>{
        setMode('WELCOME');
      }}></Header>
      <Nav topics={TOPICS} onChangeMode={(_id)=>{
        // alert(id);
        setMode('READ');
        setId(_id);
      }}></Nav>
      {content}
    </div>  
  );
}

export default App;
