import React from 'react'

export interface GeneralContextContent {
  isSignedIn: boolean
  setIsSignedIn: React.Dispatch<React.SetStateAction<boolean>>
}

export const GeneralContext = React.createContext<GeneralContextContent>({
  isSignedIn: false,
  setIsSignedIn: () => {},
})
