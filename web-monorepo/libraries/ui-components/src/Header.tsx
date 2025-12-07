import React from 'react';

export const Header = ({ title, color }: { title: string; color: string }) => {
  return (
    <header style={{ 
      backgroundColor: color, 
      padding: '1rem', 
      color: 'white', 
      marginBottom: '20px',
      fontFamily: 'Arial, sans-serif'
    }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <h2 style={{ margin: 0 }}>GenSeven</h2>
        <span style={{ backgroundColor: 'rgba(255,255,255,0.2)', padding: '5px 10px', borderRadius: '4px' }}>
          {title}
        </span>
      </div>
    </header>
  );
};