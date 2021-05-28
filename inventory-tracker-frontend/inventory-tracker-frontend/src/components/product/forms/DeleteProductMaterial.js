import { useContext } from 'react'; 
import { useParams } from 'react-router';
import MessageContext from '../../../context/MessageContext';

function DeleteProductMaterial({
    materialId,
    materialName,
    setShowPMDeleteCard,
}) {
    const { productId } = useParams();
    const { setMessages } = useContext(MessageContext);

    const confirm = (evt) => {
        evt.preventDefault()
        fetch(`http://localhost:8080/api/productMaterial/${productId}/${materialId}`, {
            method: "DELETE", 
        }) 
            .then((response) => { 
                if (response.ok) {
                    setMessages([`Your ${materialName} was successfully deleted.`]);
                    setShowPMDeleteCard(false);
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
        setShowPMDeleteCard(false);
    }

    return(
        <div className="card">
            <div className="card-content">
                <span className="card-title">Are you sure you want to delete your {materialName}?</span>
                <button className="waves-effect waves-light btn  red lighten-1" onClick={confirm}>Delete</button>
                <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" onClick={cancel}>Cancel</button>
            </div>
        </div>
    );
} 

export default DeleteProductMaterial;