import { useContext } from 'react';
import { useHistory, useParams } from 'react-router';
import MessageContext from '../../../context/MessageContext';

export default function DeleteProductCard({ productName, setShowDeleteProductCard }) {
    const history = useHistory();
    const { productId } = useParams();
    const { setMessages } = useContext(MessageContext);

    const confirm = () => {        
        fetch(`http://localhost:8080/api/product/${productId}`, {
            method: "DELETE", 
        }) 
            .then((response) => {
                if (response.ok) {
                    setMessages([`Your ${productName} product was successfully deleted.`]);
                    history.push("/products");
                } else {
                    response.json().then(json => {
                        if (Array.isArray(json)) {
                            setMessages(json);
                        } else {
                            setMessages([json.message])
                        }
                    });
                }
            }) 
    } 

    const cancel = () => {
        setShowDeleteProductCard(false);
    } 

    return(
        <div className="card">
            <div className="card-content">
                <span className="card-title">Are you sure you want to delete this product?</span>
                <button className="waves-effect waves-light btn  red lighten-1" onClick={confirm}>Delete</button>
                <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" onClick={cancel}>Cancel</button>
            </div>
        </div>
    );

}