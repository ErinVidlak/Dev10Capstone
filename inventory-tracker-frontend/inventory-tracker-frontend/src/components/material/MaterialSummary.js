import { Link } from "react-router-dom";

export default function MaterialSummary({
  materialId,
  materialName,
  pricePerUnit,
}) {
  function formatPricePerUnit(pricePerUnit) {
    return pricePerUnit.toFixed(2);
  }
  return (
    <tr>
      <td>{materialId}</td>
      <td>{materialName}</td>
      <td>${formatPricePerUnit(pricePerUnit)}</td>
      <td>
        <Link to={`/materials/${materialId}`}>
          <button className="btn waves-effect waves-light btn-flat teal accent-1 black-text">
            View
          </button>
        </Link>
      </td>
    </tr>
  );
}
