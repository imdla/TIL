import React from 'react'
import Section from './Section'
import About from './About'
import Contact from './Contact'
import More from './More'

export default function Main() {
  return (
    <main>
      <Section>
        <About></About>
      </Section>
      <Section>
        <Contact></Contact>
      </Section>
      <Section>
        <More></More>
      </Section>
    </main>
  )
}
