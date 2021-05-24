const apiUrl = "http://localhost:8080/api/material";

//find all materials
export async function findAll() {
	  const response = await fetch(apiUrl);

    if (response.status !== 200) {
      return Promise.reject(response.status + " response is not 200 OK");
    }

    return response.json();
}

//find material by Id
export async function findById(materialId) {
	  const response = await fetch(`${apiUrl}/${materialId}`);

    if (response.status !== 200) {
      return Promise.reject(response.status + " response is not 200 OK");
    }
    return response.json();
}

//add material
export async function addMaterial(material) {
		  const init = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(material),
      };

      const response = await fetch(apiUrl, init);

      if (response.status !== 201) {
        //TODO: remove print statement after function confirmed working
        console.log(material);
        return Promise.reject(response.status + " response not 201 CREATED");
      }
}

//update material
export async function updateMaterial(material) {
	 const init = {
     method: "PUT",
     headers: {
       "Content-Type": "application/json",
     },
     body: JSON.stringify(material),
   };

   const response = await fetch(`${apiUrl}/${material.materialId}`, init);
   await response.text();
   if (response.status !== 204) {
     //TODO: remove print statement after function confirmed working
     console.log(material);
     return Promise.reject(response.status + " response not 204 NO CONTENT");
   }
}

//delete material
export async function deleteMaterial(materialId) {
		const init = {
      method: "DELETE",
    };

    const response = await fetch(`${apiUrl}/${materialId}`, init);

    if (response.status !== 204) {
      //TODO: remove print statement after function confirmed working
      console.log(materialId);
      return Promise.reject(response.status + " response not 204 NO CONTENT");
    }
}
