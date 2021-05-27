import { capitalizeEach } from '../../utils/helpers';

export default function ProductMaterialTableSummary({
    materialId,
    materialName,
    materialQuantity,
    setShowPMUpdateForm,
    setShowPMDeleteCard,
  }) {

    return (
      <tr>
        <td>{materialId}</td>
        <td>{capitalizeEach(materialName)}</td>
        <td>{materialQuantity}</td>
        <td>
        <button className="btn waves-effect waves-light btn teal accent-1 black-text" onClick={() => setShowPMUpdateForm({materialId, materialName, materialQuantity})}>Update</button>
        <button className="btn waves-effect waves-light btn teal accent-1 black-text" onClick={() => setShowPMDeleteCard({materialId, materialName})}>Delete</button>
        </td>
      </tr>
    );
  }
  