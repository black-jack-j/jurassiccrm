import React from 'react';
import "./Navbar.css"
import {  Link } from "react-router-dom";
const Navbar= () =>{
    return (
        <nav style={{ margin: 10 }}>
            <Link to="/home" style={{ padding: 5 }}>
                Jurassic CRM Home
            </Link>
            <Link to="/wiki/home" style={{ padding: 5 }}>
                Wiki Home
            </Link>
            <Link to="/wiki/admin" style={{ padding: 5 }}>
                Wiki Admin
            </Link>
            <Link to="/wiki/create" style={{ padding: 5 }}>
                Create New Page
            </Link>
        </nav>
    );
}
export default Navbar;
