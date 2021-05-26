import ProductMaterialTableSummary from "./ProductMaterialTableSummary";


export default function ProductMaterialListView({materials}) {
    return (

      <table className="striped centered">
        <thead className="deep-purple lighten-3">
          <tr>
            <th>Material ID</th>
            <th>Material Name</th>
            <th>Material Quantity Used</th>
          </tr>
        </thead>
        <tbody className="deep-purple lighten-4">
          {materials.map((pm) => (
            <ProductMaterialTableSummary
              materialId={pm.material.materialId}
              materialName={pm.material.materialName}
              materialQuantity={pm.materialQuantity} />
          ))}
        </tbody>
      </table>
  );
}