const apiUrl = "http://localhost:8080/api/materialPurchase";

// find all purchases
export async function findAll() {
  const response = await fetch(apiUrl);

  if (response.status !== 200) {
    return Promise.reject(response.status + " response is not 200 OK");
  }

  return response.json();
}

//find purchase by ID
export async function findById(purchaseId) {
  const response = await fetch(`${apiUrl}/${purchaseId}`);

  if (response.status !== 200) {
    return Promise.reject(response.status + " response is not 200 OK");
  }
  return response.json();
}

// add purchase
export async function addPurchase(materialPurchase) {
	  const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(materialPurchase),
    };

    const response = await fetch(apiUrl, init);

    if (response.status !== 201) {
		//TODO: remove print statement after function confirmed working
      console.log(materialPurchase);
      return Promise.reject(response.status + " response not 201 CREATED");
    }
}

//update purchase
export async function updatePurchase(materialPurchase) {
	 const init = {
     method: "PUT",
     headers: {
       "Content-Type": "application/json",
     },
     body: JSON.stringify(materialPurchase),
   };

   const response = await fetch(
     `${apiUrl}/${materialPurchase.purchaseId}`,
     init
   );
   await response.text();
   if (response.status !== 204) {
     //TODO: remove print statement after function confirmed working
     console.log(materialPurchase);
     return Promise.reject(response.status + " response not 204 NO CONTENT");
   }
}

//delete purchase
export async function deletePurchase(purchaseId) {
	const init = {
    method: "DELETE",
  };

  const response = await fetch(`${apiUrl}/${purchaseId}`, init);

  if (response.status !== 204) {
    //TODO: remove print statement after function confirmed working
    console.log(purchaseId);
    return Promise.reject(response.status + " response not 204 NO CONTENT");
  }
}
