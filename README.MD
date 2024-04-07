# Inventory Management System
This project aims to create a RESTful API for an Inventory Management System. It includes documentation for the design of the API using the OpenAPI Specification 3.1.0, as well as implementation details using the Spring Boot framework.
## Product Resource
The product resource stores information about each product in the inventory, including its name, id , price . The resource includes the following attributes:


| Attribute    | Type   | Description                        |
|--------------|--------|------------------------------------|
| productId    | string | Unique identifier for the product. |
| name         | string | Name of the product.               |
| price        | number | Price of the product.              |

### Relationships

- Associated with Order details (one to many).
- Associated with Stock (one to one).

## Collection Resource (/products)

| HTTP Request          | Operation Description                     | HTTP Status Codes | Request Sample                                             | Response Sample                        |
|-----------------------|-------------------------------------------|-------------------|------------------------------------------------------------|---------------------------------------|
| GET /products         | Retrieve all products from the inventory | 200 OK            | None                                                       | [{ "productId": "1", "name": "Product 1", "price": 10 }] |
| POST /products        | Create a new product                      | 201 Created       | { "name": "Product 2", "price": 20 }                      | { "productId": "2", "name": "Product 2", "price": 20 } |
| GET /products/{productId} | Retrieve a product by its ID          | 200 OK            | None                                                       | { "productId": "1", "name": "Product 1", "price": 10 } |
| PUT /products/{productId} | Update an existing product            | 200 OK            | { "name": "Updated Product", "price": 15 }                | { "productId": "1", "name": "Updated Product", "price": 15 } |
| PATCH /products/{productId} | Partially update an existing product | 200 OK            | { "name": "New Name" }                                    | { "productId": "1", "name": "New Name", "price": 10 } |
| DELETE /products/{productId} | Delete a product from the inventory | 204 No Content    | None                                                       | None                                      |



## Customer Resource

The Customer resource represents information about a customer within the inventory management system. Each customer is identified by a unique customerId attribute. The resource includes the following attributes:


| Attribute    | Type   | Description                        |
|--------------|--------|------------------------------------|
| customerId   | string | Unique identifier for the customer.|
| name         | string | Name of the customer.              |
| phoneNumber  | string | Phone number of the customer.      |
| address      | string | Address of the customer.           |

### Relationships

- Associated with Orders: Each customer can have multiple orders associated with them.

### Documentation

| HTTP Request           | Operation Description                           | HTTP Status Codes | Request Sample                                                  | Response Sample                            |
|------------------------|--------------------------------------------------|-------------------|------------------------------------------------------------------|-------------------------------------------|
| GET /customers         | Retrieve all customers from the database.       | 200 OK            | None                                                             | [{ "customerId": "1", "name": "Customer 1", "phoneNumber": "1234567890", "address": "Address 1" }] |
| POST /customers        | Create a new customer.                          | 201 Created       | { "name": "Customer 2", "phoneNumber": "9876543210", "address": "Address 2" } | { "customerId": "2", "name": "Customer 2", "phoneNumber": "9876543210", "address": "Address 2" } |
| GET /customers/{customerId} | Retrieve a customer by their ID.             | 200 OK            | None                                                             | { "customerId": "1", "name": "Customer 1", "phoneNumber": "1234567890", "address": "Address 1" } |
| PUT /customers/{customerId} | Update an existing customer.                | 200 OK            | { "name": "Updated Customer", "phoneNumber": "5555555555", "address": "Updated Address" } | { "customerId": "1", "name": "Updated Customer", "phoneNumber": "5555555555", "address": "Updated Address" } |
| PATCH /customers/{customerId} | Partially update an existing customer.     | 200 OK            | { "name": "New Name" }                                          | { "customerId": "1", "name": "New Name", "phoneNumber": "1234567890", "address": "Address 1" } |
| DELETE /customers/{customerId} | Delete a customer from the database.       | 204 No Content    | None                                                             | None                                      |

## Stock Resource
The Stock resource provides information about the stock levels of products within the inventory management system. It allows tracking the quantity of each product available in the inventory. Key attributes of the Stock resource include:

| Attribute    | Type   | Description                                     |
|--------------|--------|-------------------------------------------------|
| productId    | string | Unique identifier for the product.              |
| quantity     | number | Quantity of the product available in the stock. |
| productName     | string | A string representing the name of the product. |

### Relationships

- Associated with Products (one to one).

## Collection Resource (/stock)

| HTTP Request          | Operation Description                                     | HTTP Status Codes | Request Sample                                        | Response Sample                                     |
|-----------------------|-----------------------------------------------------------|-------------------|-------------------------------------------------------|-----------------------------------------------------|
| GET /stock            | Retrieve all stock levels for products from the inventory | 200 OK            | None                                                  | [{ "productId": "1", "productName": "Product A", "quantity": 100 }] |
| POST /stock           | Create a new stock level for a product                   | 201 Created       | { "productId": "2", "productName": "Product B", "quantity": 50 }         | { "productId": "2", "productName": "Product B", "quantity": 50 }     |
| GET /stock/{productId} | Retrieve the stock level for a specific product          | 200 OK            | None                                                  | { "productId": "1", "productName": "Product A", "quantity": 100 }    |
| PUT /stock/{productId} | Update the stock level for a specific product           | 200 OK            | { "quantity": 150 }                                  | { "productId": "1", "productName": "Product A", "quantity": 150 }    |
| PATCH /stock/{productId} | Partially update the stock level for a specific product | 200 OK            | { "quantity": 120 }                                  | { "productId": "1", "productName": "Product A", "quantity": 120 }    |
| DELETE /stock/{productId} | Delete the stock level for a specific product         | 204 No Content    | None                                                  | None                                                |


## Order Resource

This table records information about each order placed by customers, including the order date, total amount, and customer details. It serves as a central repository for order data, facilitating order tracking and management.

| Attribute    | Type   | Description                              |
|--------------|--------|------------------------------------------|
| orderId      | string | Unique identifier for the order.         |
| orderDate    | string | Date and time when the order was placed.|
| customerId   | string | Unique identifier for the customer.      |
| totalPrice   | number | Total price of the order.                |
| status       | string | Status of the order (e.g., "Pending", "Shipped", "Delivered"). |

### Relationships

- Associated with Customers (many to one).
- Associated with OrderDetails (one to many).

## Collection Resource (/orders)

| HTTP Request          | Operation Description                                     | HTTP Status Codes | Request Sample                                                | Response Sample                           |
|-----------------------|-----------------------------------------------------------|-------------------|---------------------------------------------------------------|------------------------------------------|
| GET /orders           | Retrieve all orders from the database.                    | 200 OK            | None                                                          | [{ "orderId": "1", "orderDate": "2024-04-05", "customerId": "1", "totalPrice": 100, "status": "Pending" }] |
| POST /orders          | Create a new order.                                       | 201 Created       | { "orderDate": "2024-04-05", "customerId": "2", "totalPrice": 150, "status": "Pending", "orderItems": [{ "productId": "1", "quantity": 2, "price": 50 }, { "productId": "2", "quantity": 1, "price": 50 }] } | { "orderId": "2", "orderDate": "2024-04-05", "customerId": "2", "totalPrice": 150, "status": "Pending", "orderItems": [{ "productId": "1", "quantity": 2, "price": 50 }, { "productId": "2", "quantity": 1, "price": 50 }] } |
| GET /orders/{orderId} | Retrieve an order by its ID.                              | 200 OK            | None                                                          | { "orderId": "1", "orderDate": "2024-04-05", "customerId": "1", "totalPrice": 100, "status": "Pending" } |
| PUT /orders/{orderId} | Update an existing order.                                 | 200 OK            | { "orderDate": "2024-04-06", "customerId": "2", "totalPrice": 200, "status": "Shipped" } | { "orderId": "1", "orderDate": "2024-04-06", "customerId": "2", "totalPrice": 200, "status": "Shipped" } |
| PATCH /orders/{orderId} | Partially update an existing order.                      | 200 OK            | { "status": "Delivered" }                                   | { "orderId": "1", "orderDate": "2024-04-06", "customerId": "2", "totalPrice": 200, "status": "Delivered" } |
| DELETE /orders/{orderId} | Delete an order from the database.                       | 204 No Content    | None                                                          | None                                     |


## Order Details Resource

This table captures the details of each product included in an order, such as the quantity ordered, unit price, and total price. It establishes the relationship between orders and products and allows for accurate tracking of inventory movements.

### Description

| Attribute    | Type   | Description                              |
|--------------|--------|------------------------------------------|
| orderId      | string | Unique identifier for the order.         |
| productId    | string | Unique identifier for the product.       |
| price        | number | Price of the product.                    |
| quantity     | number | Quantity of the product in the order.    |
| totalPrice   | number | Total price for each product in the order.|

### Relationships

- Associated with Orders (many to one).
- Associated with Products (many to one).

## Collection Resource (/orderDetails)

| HTTP Request          | Operation Description                                     | HTTP Status Codes | Request Sample                                                | Response Sample                           |
|-----------------------|-----------------------------------------------------------|-------------------|---------------------------------------------------------------|------------------------------------------|
| GET /orderDetails     | Retrieve all order details from the database.               | 200 OK            | None                                                          | [{ "orderId": "1", "productId": "1", "price": 10, "quantity": 2, "totalPrice": 20 }] |
| POST /orderDetails    | Create a new order item.                                  | 201 Created       | { "orderId": "1", "productId": "2", "price": 15, "quantity": 3, "totalPrice": 45 } | { "orderId": "1", "productId": "2", "price": 15, "quantity": 3, "totalPrice": 45 } |
| GET /orderDetails/{orderId} | Retrieve an order item by its ID.                     | 200 OK            | None                                                          | { "orderId": "1", "productId": "1", "price": 10, "quantity": 2, "totalPrice": 20 } |
| PUT /orderDetails/{orderId} | Update an existing order item.                        | 200 OK            | { "price": 12, "quantity": 4, "totalPrice": 48 }                         | { "orderId": "1", "productId": "1", "price": 12, "quantity": 4, "totalPrice": 48 } |
| PATCH /orderDetails/{orderId} | Partially update an existing order item.            | 200 OK            | { "quantity": 5 }                                                        | { "orderId": "1", "productId": "1", "price": 10, "quantity": 5, "totalPrice": 50 } |
| DELETE /orderDetails/{orderId} | Delete an order item from the database.             | 204 No Content    | None                                                                      | None                                     |


## ERD
![ER Diagram](/image/inventory.jpg)

## Design my APIs using OpenAPI specification
[Design link](https://app.swaggerhub.com/apis/nabilaothman/Assi1/1.0.0#/)

## How to run the application 
1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Build the application using Maven.
4. Run the application using the appropriate command.
5. Access the application through a web browser or API client.


