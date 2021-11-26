import { Switch, Route } from "react-router-dom";
import DetailView from "../views/DetailView";
import ProfileView from '../views/ProfileView';
import WelcomeView from '../views/WelcomeView'

const MyRoute = () => {
    return (
        <Switch>
            <Route path='/' exact component={WelcomeView} />
            <Route path='/:category' exact component={ProfileView} />
            <Route path='/:category/:id' exact component={DetailView} />
        </Switch>
    );
}


export default MyRoute;
