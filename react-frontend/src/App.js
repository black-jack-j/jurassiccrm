import React from 'react';
import './App.css';
import {
    BrowserRouter as Router,
    Routes,
    Route,
} from 'react-router-dom';
import WikiHome from "./pages/WikiHome";
import WikiAdmin from "./pages/WikiAdmin";
import JurassicCRMHome from "./pages/JurassicCRMHome";
import Navbar from "./components/Navbar";
import WikiPage from "./pages/WikiPage";
import WikiEditor from "./pages/WikiEditor";
import WikiCreate from "./pages/WikiCreate";


function App() {
    return (
        <Router>
            <Navbar></Navbar>
            <Routes>
                <Route path="/home" element={<JurassicCRMHome />} />
                <Route path="/wiki/home" element={<WikiHome />} />
                <Route path="/wiki/admin" element={<WikiAdmin />}/>
                <Route path="/wiki/page/:title" element={<WikiPage />}/>
                <Route path="/wiki/edit/:title" element={<WikiEditor />}/>
                <Route path="/wiki/create" element={<WikiCreate />}/>
            </Routes>
        </Router>
    );
}

export default App;