export default function ProductMaterialTableSummary({
    materialId,
    materialName,
    materialQuantity,
  }) {
    return (
      <tr>
        <td>{materialId}</td>
        <td>{materialName}</td>
        <td>{materialQuantity}</td>
      </tr>
    );
  }
  