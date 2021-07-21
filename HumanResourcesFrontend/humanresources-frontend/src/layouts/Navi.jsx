import React, {useState} from "react";
import CartSummary from "./CartSummary";
import { Link } from "react-router-dom";
import { Container, Button,Menu,Icon } from "semantic-ui-react";
import SignedOut from "./SignedOut";
import SignedIn from "./SignedIn";
import { useHistory } from "react-router";
import { useSelector } from 'react-redux';

export default function Navi() {
  
  const {authItem} = useSelector(state => state.auth)

  const {cartItems} = useSelector(state => state.cart)
  const [isAuthenticated, setIsAuthenticated] = useState(true)
  const history = useHistory()
  function handleSignOut() {
    setIsAuthenticated(false)
    history.push("/")
  }

  function handleSignIn() {
    setIsAuthenticated(true)
  }

  return (
    <div>
      <Menu inverted fixed="top">
        <Container>

        <Menu.Item name="Home" as={Link} to={"/"}>          <Icon name="home" /> Home   </Menu.Item>

        <Menu.Item name="Job Advertisement" as={Link} to={"/jobadv"} />
          <Menu.Item name="Cv's'" as={Link} to={"/cvs"} />

          <Menu.Menu position="right" style={{ margin: '0.5em' }}>
            {authItem[0].loggedIn && authItem[0].user.userType===2 &&  <Button primary as={Link} to={"/jobAdvCreate"}>
            Add Insert
            </Button>}
            {authItem[0].loggedIn && authItem[0].user.userType===1 &&  <Button color="red" as={Link} to={`/jobAdvFavorites`}>
              <Icon name='heart' />
              Favorite Ad
            </Button>}
            
            {authItem[0].loggedIn?<SignedIn/>:<SignedOut/>}
          </Menu.Menu>

          {/* <Menu.Item name="home">  <Icon name="home" /> </Menu.Item> */}
          {/* <Menu.Item name="messages" /> */}

          {/* <Menu.Menu position="right">
            {cartItems.length>0&&<CartSummary/>}
            {isAuthenticated?<SignedIn signOut={handleSignOut} bisey="1"/>
            :<SignedOut signIn={handleSignIn}/>}  
          </Menu.Menu> */}
        </Container>
      </Menu>
    </div>
  );
}