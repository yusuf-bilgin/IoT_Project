
import './App.css';
import Navbar from './components/nav-bar';
import Footer from './components/footer';
import Main from './components/main';


import { BrowserRouter as Router } from 'react-router-dom';

function App() {
  return (
    <div className="App d-flex flex-column h-100">

      <Router>

        {/* Header- Sticky Navbar */}
        <header className="App-header">
          <Navbar />
        </header>
        {/* Main Content */}
        <Main />
        {/* Sticky Footer */}
        <Footer />

      </Router>
    </div>
  );
}

export default App;
