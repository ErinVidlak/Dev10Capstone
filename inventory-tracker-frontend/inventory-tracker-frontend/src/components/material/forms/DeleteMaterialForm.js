import { deleteMaterial } from "../../../services/materialAPI";
import { useState, useEffect, useContext } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import MessageContext from "../../../context/MessageContext";

export default function DeleteMaterialForm({
  initialMaterial,
  setShowDeleteForm,
}) {
  const [material, setMaterial] = useState(initialMaterial);
  const { setMessages } = useContext(MessageContext);
  const history = useHistory();

  useEffect(() => {
    setMaterial(initialMaterial);
  }, [initialMaterial]);

  async function handleClick(evt) {
    let nextMaterial = { ...material };
    nextMaterial.pricePerUnit = parseInt(material.pricePerUnit).toFixed(2);
    setMaterial(nextMaterial);

    evt.preventDefault();
    evt.stopPropagation();

    const response = await deleteMaterial(nextMaterial.materialId);  
    if (response.ok) { 
      setMessages([`Your ${nextMaterial.materialName} was successfully deleted.`]);
    } else {
      response.json().then(json => {
          if (Array.isArray(json)) {
              setMessages(json);
          } else {
              setMessages([json.message])
          }
      });
    }

    history.push("/materials");
  }

  const cancel = async () => {
    await setShowDeleteForm(false);
    history.push("/materials/" + material.materialId);
  };

  return (
    <div className="card">
      <div className="card-content">
        <span className="card-title">
          Are you sure you want to delete this material?
        </span>
        <button
          className="btn waves-effect waves-light btn-flat deep-purple lighten-3"
          onClick={cancel}>
          Nevermind
        </button>
        <button
          className="waves-effect waves-light btn  red lighten-1"
          onClick={handleClick}>
          Delete
        </button>
      </div>
    </div>
  );
}
