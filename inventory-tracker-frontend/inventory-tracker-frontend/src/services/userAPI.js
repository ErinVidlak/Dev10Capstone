const apiUrl = "http://localhost:8080/api/user";

//find all users
export async function findAll() {
  const response = await fetch(apiUrl);

  if (response.status !== 200) {
    return Promise.reject(response.status + " response is not 200 OK");
  }

  return response.json();
}

//find users by username/userId
export async function findById(userId) {
  const response = await fetch(`${apiUrl}/${userId}`);

  if (response.status !== 200) {
    return Promise.reject(response.status + " response is not 200 OK");
  }
  return response.json();
}

//add user to application backend
export async function addAppUser(user) {
  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  };

  const response = await fetch(apiUrl, init);

  if (response.status !== 201) {
    //TODO: remove print statement after function confirmed working
    console.log(user);
    return Promise.reject(response.status + " response not 201 CREATED");
  }
}
