import './App.css';
import { BrowserRouter, Switch, Route, Link } from "react-router-dom";
import MyRoute from './routes/MyRoute';

function App() {
  return (
    <BrowserRouter>
      <Route render={props => (
        <>
          {/* <Header {...props} /> */}
          <MyRoute />
          {/* <Footer /> */}
        </>
      )} />
    </BrowserRouter>
  );
}


export default App;
