import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [dark, setDark] = useState(false)

  return (
    <div className={dark ? "app dark" : "app"}>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>{dark ? "🌙 Chế độ Tối" : "☀️ Chế độ Sáng"}</h1>
      <div className="card">
        <button onClick={() => setDark(!dark)}>
          Bật {dark ? "Sáng" : "Tối"}
        </button>
        <p>
          Edit <code>src/App.tsx</code> để đổi logic vui hơn nữa
        </p>
      </div>
      <p className="read-the-docs">
        Click vào logo để học thêm về Vite và React
      </p>
    </div>
  )
}

export default App
