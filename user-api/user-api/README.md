
# Dev10 User API

## Running the application using Docker

Run this command from the root of the project to build out the Docker images and containers for the API:

```
docker-compose up
```

Once the images and containers have been created and started, you can use the supplied HTTP requests in the `http` folder to test your application using the [Visual Studio Code REST Client extension](https://marketplace.visualstudio.com/items?itemName=humao.rest-client).

Press `CTRL+C` to stop your application.

To remove the containers that were created by Docker Compose, run the following command:

```
docker-compose down
```

Include the `-v` option if you'd like to remove the persistent Docker volume that the API's database stores data within:

```
docker-compose down -v
```

## Running the application locally

You can also run the application locally if you'd like to develop or debug using IntelliJ.

Create your databases (production and test) by running the following database scripts in MySQL Workbench:

* [`database-scripts/schema-data.sql`](./database-scripts/schema-data.sql)
* [`database-scripts/schema-data-test.sql`](./database-scripts/schema-data-test.sql)

Define the following environment variables within IntelliJ for both the `Application` and `JUnit` run configuration templates:

```
DB_URL=jdbc:mysql://localhost:3306/dev10_users
DB_TEST_URL=jdbc:mysql://localhost:3306/dev10_users_test
DB_USERNAME=root
DB_PASSWORD={root account password}
```

> **Be sure to replace the `{root account password}` placeholder string with your MySQL database's root account password.**

Now you can run the application (see the `App` class' `main()` method for the application's entry point) within IntelliJ and/or run the project's unit tests.

## Testing the application

You can use the supplied [HTTP requests in the `http` folder](./http) to test your application using the [Visual Studio Code REST Client extension](https://marketplace.visualstudio.com/items?itemName=humao.rest-client).

## Seed Data

The provided database scripts seed the database with two user accounts:

```json
{
  "id": "983f1224-af4f-11eb-8368-0242ac110002",
  "username": "john@smith.com",
  "password": "P@ssw0rd!",
  "roles": "ADMIN"
}
```

```json
{
  "id": "9e5d9272-af4f-11eb-8368-0242ac110002",
  "username": "sally@jones.com",
  "password": "P@ssw0rd!",
  "roles": "USER"
}
```

You can use these test accounts when testing the API.

## Endpoints

The API provides the following endpoints for creating a new user account, authenticating an existing user, and refreshing a non-expired token.

### http://localhost:5000/create_account

Used to create a new user account.

**Request**

```
POST http://localhost:5000/create_account HTTP/1.1
Content-Type: application/json

{
  "username": "smashdev",
  "password": "Asdff88f67!"
}
```

**Response**

```
HTTP/1.1 201 

{
  "id": "a312e741-f33c-4adb-946c-a6a44cfe26be"
}
```

**Example Error Response**

```
HTTP/1.1 400 

{
  "messages": [
    "`password` is required.",
    "`password` must be between 8 and 50 characters.",
    "`username` is required."
  ]
}
```

### http://localhost:5000/authenticate

Used to authenticate an existing user.

**Request**

```
POST http://localhost:5000/authenticate HTTP/1.1
Content-Type: application/json

{
    "username": "john@smith.com",
    "password": "P@ssw0rd!"
}
```

**Response**

```
HTTP/1.1 200 

{
  "jwt_token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZXYxMC11c2Vycy1hcGkiLCJzdWIiOiJqb2huQHNtaXRoLmNvbSIsImlkIjoiOTgzZjEyMjQtYWY0Zi0xMWViLTgzNjgtMDI0MmFjMTEwMDAyIiwicm9sZXMiOiJBRE1JTiIsImV4cCI6MTYyMDQ5NTMwNn0.Okwn3JO1UPrwKDw-SFh19HlQVOr4wqwYISUDh5kxmlg"
}
```

**Token Payload**

Tokens returned from the API contain the following payload:

```json
{
  "iss": "dev10-users-api",
  "sub": "john@smith.com",
  "id": "983f1224-af4f-11eb-8368-0242ac110002",
  "roles": "ADMIN",
  "exp": 1620495306
}
```

* `iss` - the issuer of the token (i.e. the name of the API)
* `sub` - the subject (i.e. the user's username)
* `id` - the user's unique ID
* `roles` - the user's assigned roles (will either be `USER` or `ADMIN`; new user accounts are assigned the role `USER` by default)
* `exp` - the expiration date of the token (measured in milliseconds from midnight January 1, 1970 UTC)

> For more information about JWTs see [jwt.io](https://jwt.io/).

**Example Error Response**

For security reasons, the API will only return a `403 FORBIDDEN` HTTP status code if the authentication process fails for any reason.

```
HTTP/1.1 403
```

### http://localhost:5000/refresh_token

Used to refresh a non-expired token.

**Request**

```
POST http://localhost:5000/refresh_token HTTP/1.1
Content-Type: application/json

{
  "jwt_token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZXYxMC11c2Vycy1hcGkiLCJzdWIiOiJqb2huQHNtaXRoLmNvbSIsImlkIjoiOTgzZjEyMjQtYWY0Zi0xMWViLTgzNjgtMDI0MmFjMTEwMDAyIiwicm9sZXMiOiJBRE1JTiIsImV4cCI6MTYyMDQyODA5NX0.HrFalpusNo88aNCJXYtrGp5gOGMvinEQXI1QPstw-2M"
}
```

**Response**

```
HTTP/1.1 200 

{
  "jwt_token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZXYxMC11c2Vycy1hcGkiLCJzdWIiOiJqb2huQHNtaXRoLmNvbSIsImlkIjoiOTgzZjEyMjQtYWY0Zi0xMWViLTgzNjgtMDI0MmFjMTEwMDAyIiwicm9sZXMiOiJBRE1JTiIsImV4cCI6MTYyMDQ5NTk3M30.CxYariCRb7UWqhV2Mg3A_IpkYhAkVxd16bp2gO8SALQ"
}
```

**Example Error Response**

For security reasons, the API will only return a `403 FORBIDDEN` HTTP status code if the token refresh process fails for any reason.

```
HTTP/1.1 403
```
