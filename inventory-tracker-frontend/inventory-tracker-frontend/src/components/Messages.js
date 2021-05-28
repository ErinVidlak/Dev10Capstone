import { useLocation } from 'react-router-dom';

function Messages({messages}) {
    const location = useLocation();
    const isNotProductListing = () => {
        console.log(location)
        const path = location.pathname;
        return !path.includes("listing") || !path.includes("products");
    }
    return (
        <>
        {isNotProductListing() && (
            <div className="card">
                <div className="card-content">
                    <span className="card-title">Messages</span>
                    <ul className="collection">
                        {messages.map(message => 
                        <li className="collection-item" key={message}>{message}</li>)}
                    </ul>
                </div>
            </div>
        )}
        </>
    )
} 

export default Messages;