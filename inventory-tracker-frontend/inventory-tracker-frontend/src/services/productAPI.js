const apiUrl = "http://localhost:8080/api/product";


//find all product
export async function findAll() {
	  const response = await fetch(apiUrl);

    if (response.status !== 200) {
      return Promise.reject(response.status + " response is not 200 OK");
    }

    return response.json();
}

//find product by Id
export async function findById(productId) {
	  const response = await fetch(`${apiUrl}/${productId}`);

    if (response.status !== 200) {
      return Promise.reject(response.status + " response is not 200 OK");
    }
    return response.json();
}

//add product
export async function addProduct(product) {
  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(product),
  };

  const response = await fetch(apiUrl, init);

  if (response.status !== 201) {
    //TODO: remove print statement after function confirmed working
    console.log(product);
    return Promise.reject(response.status + " response not 201 CREATED");
  }
}

//update product
export async function updateProduct(product) {
  const init = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(product),
  };

  const response = await fetch(`${apiUrl}/${product.productId}`, init);
  await response.text();
  if (response.status !== 204) {
    //TODO: remove print statement after function confirmed working
    console.log(product);
    return Promise.reject(response.status + " response not 204 NO CONTENT");
  }
}

//delete product
export async function deleteProduct(productId) {
  const init = {
    method: "DELETE",
  };

  const response = await fetch(`${apiUrl}/${productId}`, init);

  if (response.status !== 204) {
    //TODO: remove print statement after function confirmed working
    console.log(productId);
    return Promise.reject(response.status + " response not 204 NO CONTENT");
  }
}
