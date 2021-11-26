import './App.css';
import { BrowserRouter, Route } from "react-router-dom";
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
