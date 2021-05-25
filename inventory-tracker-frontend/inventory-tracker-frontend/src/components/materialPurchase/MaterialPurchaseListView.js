import { useContext, useState, useEffect } from "react";
import { findAll } from "../../services/materialPurchaseAPI";
import MaterialPurchaseSummary from "./MaterialPurchaseSummary";
import Messages from "../Messages";
import MessageContext from "../../context/MessageContext";
import { useHistory } from 'react-router';

export default function MaterialPurchaseListView() {
    const [materialPurchaseList, setMaterialPurchaseList] = useState([]);
    const {messages} = useContext(MessageContext);
    const history = useHistory();

    const addMaterialPurchase = () => {
        history.push("/purchases/add");
    } 

    useEffect(() => {
        findAll().then((data) => {
          setMaterialPurchaseList(data);
        });
    }, []);

    return (
    <div className="container px-3 py-3">
        <h3>View All Raw Material Purchases</h3>
        <table className="striped">
        <thead>
            <tr>
            <th>Purchase Date</th>
            <th>Material Name</th>
            <th>Cost</th>
            <th>View</th>
            </tr>
        </thead>

        <tbody>
            {materialPurchaseList.sort( (a, b) => {
                return new Date(b.datePurchased) - new Date(a.datePurchased)
            })
            .map((materialPurchase) => (
            <MaterialPurchaseSummary
                key={materialPurchase.purchaseId}
                datePurchased={materialPurchase.datePurchased}
                materialId={materialPurchase.materialId}
                purchasePrice={materialPurchase.purchasePrice}
                materialPurchaseId={materialPurchase.purchaseId}
            />
            ))}
        </tbody>
        </table>
        <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" onClick={addMaterialPurchase}>Add</button>
        {messages.length > 0 && <Messages messages={messages}/>}
    </div>
    );

}