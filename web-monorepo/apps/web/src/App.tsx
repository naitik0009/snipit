import { Header } from '@genseven/ui-components';

function App() {
  return (
    <div>
      {/* Blue Theme Header */}
      <Header title="Public Website" color="#2563eb" />
      
      <div style={{ padding: '20px', fontFamily: 'sans-serif' }}>
        <h1>GenSeven Solutions</h1>
        <p>Welcome to our public marketing page.</p>
        <button style={{
          marginTop: '20px',
          padding: '10px 20px',
          backgroundColor: '#2563eb',
          color: 'white',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer'
        }}>
          Sign Up Now
        </button>
      </div>
    </div>
  );
}

export default App;