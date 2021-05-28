import { useContext, useState, useEffect } from "react";
import { findAll } from "../../services/materialPurchaseAPI";
import MaterialPurchaseSummary from "./MaterialPurchaseSummary";
import { useHistory } from 'react-router';

export default function MaterialPurchaseListView() {
    const [materialPurchaseList, setMaterialPurchaseList] = useState([]);
    const history = useHistory();

    const addMaterialPurchase = () => {
        history.push("/purchases/add");
    } 

    const formatPurchasePrice = (purchasePrice) => {
        return purchasePrice.toFixed(2);
    }

    useEffect(() => {
        findAll().then((data) => {
          setMaterialPurchaseList(data);
        });
    }, []);

    return (
      <div className="container center">
        <div className="row center">
          <div className="card purple lighten-4">
            <div className="card-content black-text">
              <span className="card-title center">Raw Material Purchases</span>
            </div>
          </div>
        </div>

        <div className="row">
          <table className="striped centered green accent-1">
            <thead>
              <tr>
                <th>Purchase Date</th>
                <th>Material Name</th>
                <th>Cost</th>
                <th>View</th>
              </tr>
            </thead>

            <tbody>
              {materialPurchaseList
                .sort((a, b) => {
                  return new Date(b.datePurchased) - new Date(a.datePurchased);
                })
                .map((materialPurchase) => (
                  <MaterialPurchaseSummary
                    key={materialPurchase.purchaseId}
                    datePurchased={materialPurchase.datePurchased}
                    materialId={materialPurchase.materialId}
                    purchasePrice={formatPurchasePrice(
                      materialPurchase.purchasePrice
                    )}
                    materialPurchaseId={materialPurchase.purchaseId}
                  />
                ))}
            </tbody>
          </table>
        </div>
        <button
          className="btn waves-effect waves-light btn teal accent-1 black-text"
          onClick={addMaterialPurchase}>
          Add a New Purchase
        </button>
      </div>
    );

}