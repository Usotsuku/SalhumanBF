
import 
 {BsFillBellFill, BsFillEnvelopeFill, BsPersonCircle, BsSearch, BsJustify}
 from 'react-icons/bs'

 import React, { useState, useEffect } from 'react';
import UserService from '../service/UserService';
import { Link } from 'react-router-dom';

function Header({OpenSidebar}) {
    const [profileInfo, setProfileInfo] = useState({
        name: '',
        email: '',
        role: '',
    });

    useEffect(() => {
        fetchProfileInfo();
    }, []);

    const fetchProfileInfo = async () => {
        try {

            const token = localStorage.getItem('token'); // Retrieve the token from localStorage
            const response = await UserService.getYourProfile(token);
            setProfileInfo(response.user);
        } catch (error) {
            console.error('Error fetching profile information:', error);
        }
    };
  return (
    <header className='header'>
        <div className='menu-icon'>
            <BsJustify className='icon' onClick={OpenSidebar}/>
        </div>
        <div className='header-left'>
        <div><p>{profileInfo.role}</p></div>
        </div>
        <div className='header-right'>
            <div><p>{profileInfo.name}</p></div>
        </div>
    </header>
  )
}

export default Header