const apiUrl = "http://localhost:8080/api/listedProduct";

//find all product listings
export async function findAll() {
  const response = await fetch(apiUrl);

  if (response.status !== 200) {
    return Promise.reject(response.status + " response is not 200 OK");
  }

  return response.json();
}

//find product listing by Id
export async function findById(listedProductId) {
  const response = await fetch(`${apiUrl}/${listedProductId}`);

  if (response.status !== 200) {
    return Promise.reject(response.status + " response is not 200 OK");
  }
  return response.json();
}

//add product listing
export async function addProduct(listedProduct) {
  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(listedProduct),
  };

  const response = await fetch(apiUrl, init);

  if (response.status !== 201) {
    //TODO: remove print statement after function confirmed working
    console.log(listedProduct);
    return Promise.reject(response.status + " response not 201 CREATED");
  }
}

//update product listing
export async function updateProduct(listedProduct) {
  const init = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(listedProduct),
  };

  const response = await fetch(
    `${apiUrl}/${listedProduct.listedProductId}`,
    init
  );
  await response.text();
  if (response.status !== 204) {
    //TODO: remove print statement after function confirmed working
    return Promise.reject(response.status + " response not 204 NO CONTENT");
  }
}

//delete product listing
export async function deleteProduct(listedProductId) {
  const init = {
    method: "DELETE",
  };

  const response = await fetch(`${apiUrl}/${listedProductId}`, init);

  if (response.status !== 204) {
    //TODO: remove print statement after function confirmed working
    console.log(listedProductId);
    return Promise.reject(response.status + " response not 204 NO CONTENT");
  }
}
