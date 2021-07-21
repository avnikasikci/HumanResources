import React from 'react'
import { Button, Menu } from 'semantic-ui-react'
import { Link } from "react-router-dom";

export default function SignedOut({signIn}) {
    return (
        // <div>
        //     <Menu.Item>
        //        <Button  onClick={signIn} primary>Giriş yap</Button>
        //        <Button primary style={{marginLeft:'0.5em'}}>Kayıt Ol</Button> 
        //     </Menu.Item>
            
        // </div>

<div>
<Button.Group>
  <Button as={Link} to={"/login"}>Giriş yap</Button>
  <Button.Or />
  <Button positive as={Link} to={"/register"}>Kaydol</Button>
</Button.Group>
</div>
    )
}
