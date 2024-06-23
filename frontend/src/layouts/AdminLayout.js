
import Header from '../components/common/Header';
import Sidebar from '../components/common/Sidebar';
import React from 'react';
import { Outlet } from 'react-router-dom';

function AdminLayout({ openSidebarToggle, OpenSidebar }) {
  return (
    <div className="admin-layout">
      <Sidebar openSidebarToggle={openSidebarToggle} OpenSidebar={OpenSidebar} />
      <div className="main-content">
        {/* This will render the matched child route component */}
        <Outlet />
      </div>
    </div>
  );
}

export default AdminLayout;

