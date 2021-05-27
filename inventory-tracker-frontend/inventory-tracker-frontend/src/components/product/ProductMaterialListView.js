import ProductMaterialTableSummary from "./ProductMaterialTableSummary";


export default function ProductMaterialListView({ materials, setShowPMUpdateForm, setShowPMDeleteCard }) {
    return (

      <table className="striped centered">
        <thead className="deep-purple lighten-3">
          <tr>
            <th>Material ID</th>
            <th>Material Name</th>
            <th>Material Quantity Used</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody className="deep-purple lighten-4">
          {materials.map((pm) => (
            <ProductMaterialTableSummary
              key={pm.material.materialId}
              materialId={pm.material.materialId}
              materialName={pm.material.materialName}
              materialQuantity={pm.materialQuantity}
              setShowPMUpdateForm={setShowPMUpdateForm}
              setShowPMDeleteCard={setShowPMDeleteCard}/>
          ))}
        </tbody>
      </table>
  );
}