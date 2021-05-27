function Messages({messages}) {
    return (  
        <div className="card">
            <div className="card-content">
                <span className="card-title">Messages</span>
                <ul className="collection">
                    {messages.map(message => 
                    <li className="collection-item" key={message}>{message}</li>)}
                </ul>
            </div>
        </div>
    )
} 

export default Messages;