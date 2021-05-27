import { useState } from "react";
import UpdateProductMaterial from "./forms/UpdateProductMaterial";

export default function ProductMaterialTableSummary({
    materialId,
    materialName,
    materialQuantity,
    setShowPMUpdateForm,
  }) {

    return (
      <tr>
        <td>{materialId}</td>
        <td>{materialName}</td>
        <td>{materialQuantity}</td>
        <td>
        <button className="btn waves-effect waves-light btn teal accent-1 black-text" onClick={() => setShowPMUpdateForm({materialId, materialName, materialQuantity})}>Update</button>
        <button className="btn waves-effect waves-light btn teal accent-1 black-text">Delete</button>
        </td>
      </tr>
    );
  }
  