import { useState } from 'react'; 
import { useHistory } from 'react-router';

function AddMaterialPurchase({setMessages}) {
    const history = useHistory();
    const [materialPurchase, setMaterialPurchase] = useState({});

    const submit = (evt) => {
        evt.preventDefault()  
        fetch("http://localhost:8080/api/materialPurchase", {
            method: "POST", 
            headers: { "Content-Type": "application/json"}, 
            body: JSON.stringify(materialPurchase),
        }) 
            .then((response) => {
                if (response.ok) { 
                    setMessages([`Your ${material.materialName} purchase was successfully added.`]);
                } else {
                    response.json().then(json => {
                        if (Array.isArray(json)) {
                            setMessages(json);
                        } else {
                            setMessages([json.message])
                        }
                    });
                }
                history.push("/materialPurchases");
            })  
    } 

    const cancel = () => {
        history.push("/materialPurchases");
    }

    return(
        <>
        <h4>Add a Raw Material Purchase</h4>
        <form onSubmit={submit}>
            <div>
                <label htmlFor="datePurchased">Purchase Date</label>
                    <input type="date" id="datePurchased" name="materialPurchase[datePurchased]" onChange={evt => setMaterialPurchase({ ... materialPurchase, datePurchased: evt.target.value})}/>
            </div>
            <div>
                <label htmlFor=""></label>
            </div>
            <div>
            <button className="btn waves-effect waves-light" type="submit" name="action">Add Purchase</button>  <button className="btn waves-effect waves-light" type="button" onClick={cancel}>Cancel</button>
            </div>
            <script public="index.html"></script>
        </form>
        </>
    );
} 

export default AddMaterialPurchase;