import { useHistory, useParams } from 'react-router';

function DeleteCard({materialName, setMessages}) {
    const history = useHistory();
    const { purchaseId } = useParams();

    const confirm = () => {        
        fetch(`http://localhost:8080/api/materialPurchase/${purchaseId}`, {
            method: "DELETE", 
        }) 
            .then((response) => {
                if (response.ok) {
                    setMessages([`Your ${materialName} purchase was successfully deleted.`]);
                } else {
                    response.json().then(json => {
                        if (Array.isArray(json)) {
                            setMessages(json);
                        } else {
                            setMessages([json.message])
                        }
                    });
                }
                history.push("/purchases");
            }) 
    }

    const cancel = () => {
        history.push("/purchases");
    }

    return(
        <div className="card">
            <div className="card-content">
                <span className="card-title">Are you sure you want to delete this purchase?</span>
                <button className="waves-effect waves-light btn  red lighten-1" onClick={confirm}>Delete</button>
                <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" onClick={cancel}>Cancel</button>
            </div>
        </div>
    );
} 

export default DeleteCard;