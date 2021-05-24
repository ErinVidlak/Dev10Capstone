import { useState, useEffect } from 'react'; 
import { useHistory, useParams } from 'react-router';

function UpdateMaterialPurchase() {
    const history = useHistory();
    const { id } = useParams();
    const [materialPurchase, setMaterialPurchase] = useState(); 

    useEffect(() => {
        fetch(`http://localhost:8080/api/materialPurchase/${id}`)
        .then(response => response.json())
        .then(data => setAgent(data))
        .catch(error => console.log(error));
    }, [id]);

    const submit = (evt) => {
        evt.preventDefault()  
        fetch(`http://localhost:8080/api/materialPurchase/${id}`, {
            method: "PUT", 
            headers: { "Content-Type": "application/json"}, 
            body: JSON.stringify(materialPurchase),
        }) 
            .then((response) => { 
                if (response.ok) {
                    setMessages([`Your ${material.materialName} purchase was successfully updated.`]);
                } else {
                    response.json().then(json => {
                        if (Array.isArray(json)) {
                            setMessages(json);
                        } else {
                            setMessages([json.message])
                        }
                    });
                } 
                history.push("/materialPurchase");
            }) 
    }

    const cancel = () => {
        history.push("/materialPurchase");
    }

    return (
        <>
        {materialPurchase && (
            <>
            <h4>Update a Purchase</h4>
            <form onSubmit={submit}>
                <div>
                <label htmlFor="datePurchased">Purchase Date</label>
                    <input type="date" id="datePurchased" name="materialPurchase[datePurchased]" onChange={evt => setMaterialPurchase({ ... materialPurchase, datePurchased: evt.target.value})}/>
                </div>
                <div>
                <label htmlFor="materialName">Material Name</label>    
                </div>
            </form>
            </>
        )}
        </>

    );
} 

export default UpdateMaterialPurchase;