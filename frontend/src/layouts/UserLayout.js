import React from 'react';
import Header from '../components/common/Header';

function UserLayout({ children }) {
  return (
    <div className="grid-container">
      <Header />
      <div className="main-content">
        {children}
      </div>
    </div>
  );
}

export default UserLayout;
