import MaterialPurchaseTableSummary from "./MaterialPurchaseTableSummary";

export default function MaterialPurchaseListView({purchases}){


	return (
    <div className="container">
      <table className="striped centered">
        <thead className="deep-purple lighten-3">
          <tr>
            <th>Purchase ID</th>
            <th>Quantity Purchased</th>
            <th>Purchase Price</th>
       
          </tr>
        </thead>
        <tbody className="deep-purple lighten-4">
          {purchases.map((p) => (
            <MaterialPurchaseTableSummary
			purchaseId={p.purchaseId}
			purchasePrice={p.purchasePrice}
			quantityPurchased={p.quantityPurchased}
            />
          ))}
        </tbody>
      </table>
    </div>
  );
}