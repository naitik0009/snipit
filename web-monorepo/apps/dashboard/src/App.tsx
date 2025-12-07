import { Header } from '@genseven/ui-components';
import { useStore } from './store';
function App() {
  const count = useStore((state)=> state.count);
  return (
    <div>
      {/* Dark Theme Header */}
      <Header title="Dashboard" color="#0f172a" />
      
      <div style={{ padding: '20px', fontFamily: 'sans-serif' }}>
        <h1>Welcome, User {count}</h1>
        <p>This is the private dashboard application.</p>
        <div style={{ 
          border: '1px solid #ddd', 
          padding: '15px', 
          borderRadius: '8px',
          marginTop: '20px',
          backgroundColor: '#f8fafc'
        }}>
          <strong>System Status:</strong> All systems operational.
        </div>
      </div>
    </div>
  );
}

export default App;