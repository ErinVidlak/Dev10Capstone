import MaterialPurchaseTableSummary from "./MaterialPurchaseTableSummary";

export default function MaterialPurchaseListView({purchases}){


	return (
    <table className="striped centered">
      <thead className="deep-purple lighten-3">
        <tr>
          <th>Purchase ID</th>
          <th>Purchase Price</th>
          <th>Quantity Purchased</th>
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
  );
}