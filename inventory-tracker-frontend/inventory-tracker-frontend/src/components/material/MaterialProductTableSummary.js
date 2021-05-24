export default function MaterialProductTableSummary({
  productId,
  productName,
  materialQuantity,
}) {
  return (
    <tr>
      <td>{productId}</td>
      <td>{productName}</td>
      <td>{materialQuantity}</td>
    </tr>
  );
}
