import React from 'react'

export default function Button2({ onClick, backgroundColor, children }) {
  return (
    <button style={{
      backgroundColor
    }} onClick={onClick}>{children}</button>
  )
}
