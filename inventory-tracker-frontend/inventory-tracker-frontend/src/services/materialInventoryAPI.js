const apiUrl = "http://localhost:8080/api/materialInventory";

// find all inventories
export async function findAll() {
  const response = await fetch(apiUrl);

  if (response.status !== 200) {
    return Promise.reject(response.status + " response is not 200 OK");
  }

  return response.json();
}

//find inventory by ID
export async function findById(materialInventoryId) {
  const response = await fetch(`${apiUrl}/${materialInventoryId}`);

  if (response.status !== 200) {
    return Promise.reject(response.status + " response is not 200 OK");
  }
  return response.json();
}

//add inventory
export async function addInventory(materialInventory) {
  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(materialInventory),
  };

  const response = await fetch(apiUrl, init);

  if (response.status !== 201) {
    //TODO: remove print statement after function confirmed working
    console.log(materialInventory);
    return Promise.reject(response.status + " response not 201 CREATED");
  }
}

//update inventory
export async function updateInventory(materialInventory) {
  const init = {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(materialInventory),
  };

  const response = await fetch(
    `${apiUrl}/${materialInventory.materialInventoryId}`,
    init
  );
  await response.text();
  if (response.status !== 204) {
    //TODO: remove print statement after function confirmed working
    console.log(materialInventory);
    return Promise.reject(response.status + " response not 204 NO CONTENT");
  }
}

//delete inventory
export async function deleteInventory(materialInventoryId) {
  const init = {
    method: "DELETE",
  };

  const response = await fetch(`${apiUrl}/${materialInventoryId}`, init);

  if (response.status !== 204) {
    //TODO: remove print statement after function confirmed working
    console.log(materialInventoryId);
    return Promise.reject(response.status + " response not 204 NO CONTENT");
  }
}
