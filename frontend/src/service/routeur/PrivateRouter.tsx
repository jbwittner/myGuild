import React from 'react'
import { Route, Redirect, RouteProps } from 'react-router-dom'
import { GeneralContext } from '../common/Context'

interface PrivateRouteProps extends RouteProps {
  component: any // eslint-disable-line @typescript-eslint/no-explicit-any
}

const PrivateRoute = (props: PrivateRouteProps): JSX.Element => {
  const { component: Component, ...rest } = props

  const { isSignedIn } = React.useContext(GeneralContext)

  return (
    // Show the component only when the user is logged in
    // Otherwise, redirect the user to /signin page
    <Route
      {...rest}
      render={(props) =>
        isSignedIn ? (
          <Component {...props} />
        ) : (
          <Redirect to={{ pathname: '/', state: { from: props.location } }} />
        )
      }
    />
  )
}

export default PrivateRoute
