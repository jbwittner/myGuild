export interface AddUserParameter {
  nickName: string
  email: string
}

export interface BackendError {
  details: string
  exception: string
  message: string
  timestamp: string
}
