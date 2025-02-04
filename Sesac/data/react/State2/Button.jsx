import React from 'react'

export default function Button({ backgroundColor, onClick, children }) {

  return (
    <button style={{
      backgroundColor
    }} onClick={onClick}>{children}</button>
  )
}
