const apiUrl = "http://localhost:8080/api/productMaterial";


//find productMaterial by productId
export async function findByProductId(productId) {
	  const response = await fetch(`${apiUrl}/${productId}`);

    if (response.status !== 200) {
      return Promise.reject(response.status + " response is not 200 OK");
    }
    return response.json();
}

//add productMaterial
export async function addProductMaterial(productMaterial) {
  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(productMaterial),
  };

  const response = await fetch(apiUrl, init);

  // if (response.status !== 201) {
  //   //TODO: remove print statement after function confirmed working
  //   console.log(productMaterial);
  //   return Promise.reject(response.status + " response not 201 CREATED");
  // }
  return response;
}

//update productMaterial
export async function updateProductMaterial(productMaterial) {
  const init = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(productMaterial),
  };

  const response = await fetch(`${apiUrl}/${productMaterial.productId}/${productMaterial.materialId}`, init);
  await response.text();
  if (response.status !== 204) {
    //TODO: remove print statement after function confirmed working
    console.log(productMaterial);
    return Promise.reject(response.status + " response not 204 NO CONTENT");
  }
}

//delete productMaterial
export async function deleteProductMaterial(productId, materialId) {
  const init = {
    method: "DELETE",
  };

  const response = await fetch(`${apiUrl}/${productId}/${materialId}`, init);

  if (response.status !== 204) {
    return Promise.reject(response.status + " response not 204 NO CONTENT");
  }
}
