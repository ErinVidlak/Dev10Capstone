import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { findById } from "../../services/materialPurchaseAPI";
import { capitalizeEach, formatPricePerUnit } from "../../utils/helpers";
import dateFormat from 'dateformat';
import { findById as findMaterialById } from '../../services/materialAPI';
import DeleteCard from './DeleteCard';
import UpdateMaterialPurchase from './UpdateMaterialPurchase';


export default function MaterialPurchaseDetailedView() {
    const { purchaseId } = useParams();
    const [showDeleteCard, setShowDeleteCard] = useState(false);
    const [showUpdateForm, setShowUpdateForm] = useState(false);

    const [material, setMaterial] = useState({
        materialName: ""
    })

    const formatPurchasePrice = (purchasePrice) => {
        return purchasePrice.toFixed(2);
    }

    const [materialPurchase, setMaterialPurchase] = useState({
        datePurchased: null,
        purchasePrice: 0.0,
        quantityPurchased: 0,
        units: "",
        description: "",
    });

    useEffect(() => {
        findById(purchaseId).then((data) => {
          setMaterialPurchase(data);
        });
    }, [purchaseId, showUpdateForm]);

    useEffect(() => {
        if (materialPurchase.materialId) {
            findMaterialById(materialPurchase.materialId).then((data) => {
                setMaterial(data);
            });
        }
    }, [materialPurchase]);

    

    return (
        <div className="container">
            <div className="row center">
                <div className="col s12">
                    <div className="card light-blue lighten-4">
                        <div className="card-content black-text">
                            <span className="card-title center">
                                {capitalizeEach(material.materialName)}
                            </span>
                        </div>
                    </div>
                </div>
            
                <div className="col s6">
                    <div className="card indigo lighten-3">
                        <div className="card-content black-text">
                            <span className="card-title">
                            Purchase Date: {dateFormat(new Date(materialPurchase.datePurchased), "paddedShortDate")}
                            </span>
                        </div>
                    </div>
                </div> 
                <div className="col s6">
                    <div className="card indigo lighten-3">
                        <div className="card-content black-text">
                            <span className="card-title">
                            Cost: ${formatPricePerUnit(materialPurchase.purchasePrice)}
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <div className="row s12">
                <table className="striped centered">
                    <thead className="deep-purple lighten-3">
                    <tr>
                        <th>Quantity</th>
                        <th>Units</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody className="deep-purple lighten-4">
                        <tr>
                            <td>{materialPurchase.quantityPurchased}</td>
                            <td>{materialPurchase.units}</td>
                            <td>{materialPurchase.description}</td>
                        </tr>
                    </tbody>
                </table>
            </div> 
            <Link to="/purchases">
            <button className=" waves-effect waves-light btn ">Back </button>
            </Link>
            <button className="btn waves-effect waves-light btn-flat deep-purple lighten-3" onClick={() => setShowUpdateForm(true)}>Update</button>
            <button className="waves-effect waves-light btn  red lighten-1" onClick={() => setShowDeleteCard(true)}>Delete</button>
            {showUpdateForm && (
                <UpdateMaterialPurchase materialName={material.materialName} materialPurchase={materialPurchase} setShowUpdateForm={setShowUpdateForm}/>
            )}
            {showDeleteCard && (
                <DeleteCard materialName={material.materialName} setShowDeleteCard={setShowDeleteCard}/>
            )}
        </div>    
    );
}