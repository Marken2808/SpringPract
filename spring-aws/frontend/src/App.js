import './App.css';
import * as ReactDOM from "react-dom";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import { ProfileView, ProfilePlayerView } from './views/profiles';
import WelcomeView from './views/WelcomeView';

function App() {
  return (
    <div className="App">
      <Routes>
          <Route path="/" element={<WelcomeView />} />
          <Route path="/profile" element={<ProfileView />} />
          <Route path="/profile/:id" component={ProfilePlayerView} />
          {/* <Route path="/profile/:id" render={({ location }) => {
            const { state } = location;
            return <ProfilePlayerView {state.profile}/>}} 
          /> */}
      </Routes>

    </div>
  );
}


export default App;
