import MaterialProductTableSummary from "./MaterialProductTableSummary";


export default function MaterialProductListView({products}) {
return (

    <table className="striped centered">
      <thead className="deep-purple lighten-3">
        <tr>
          <th>Product ID</th>
          <th>Product Name</th>
          <th>Material Quantity Used</th>
        </tr>
      </thead>
      <tbody className="deep-purple lighten-4">
        {products.map((p) => (
          <MaterialProductTableSummary
		  productId={p.product.productId}
		  productName={p.product.productName}
		  materialQuantity={p.materialQuantity} />
        ))}
      </tbody>
    </table>

);
}