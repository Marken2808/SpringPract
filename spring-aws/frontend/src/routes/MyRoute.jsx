import { Switch, Route, Link } from "react-router-dom";
import { ProfileView, ProfilePlayerView } from '../views/profiles';
import WelcomeView from '../views/WelcomeView'

const MyRoute = () => {
    return (
        <Switch>
            <Route path='/' exact component={WelcomeView} />
            <Route path='/:category' component={ProfileView} />
            <Route path='/:category/:id' component={ProfilePlayerView} />
        </Switch>
    );
}


export default MyRoute;
