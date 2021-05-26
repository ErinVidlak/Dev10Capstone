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
        <td>
        <button className="btn waves-effect waves-light btn teal accent-1 black-text">Update</button>
        <button className="btn waves-effect waves-light btn teal accent-1 black-text">Delete</button>
        </td>
      </tr>
    );
  }
  