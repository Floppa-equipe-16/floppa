# Floppa


![example workflow](https://github.com/Floppa-equipe-16/floppa/actions/workflows/maven.yml/badge.svg)
![contributors](https://img.shields.io/github/contributors/Floppa-equipe-16/floppa)
![issues](https://img.shields.io/github/issues/Floppa-equipe-16/floppa)
![license](https://img.shields.io/github/license/Floppa-equipe-16/floppa)

Floppa is a API for a marketplace or auction website that allows sellers to list and sell their products, and buyers to browse and purchase products from various sellers.

<details>
<summary>How to run</summary>

### Requirements
- Java 11
- Maven

### Environment variables
For the application to work, you need to set the following environment variables:

#### _Database_
```
FLOPPA_MONGO_CLUSTER_URL=[A MongoDB cluster url]
FLOPPA_MONGO_DATABASE=[A MongoDB database name]
```
#### _Email Service_ (Optional)
```
FLOPPA_HOST_EMAIL=[Whatever email you want to use as the sender for the email service]
FOPPA_HOST_PASSWORD=[The app password for the particular email]
```
### Commands
#### Build
```
mvn clean install
```
#### Execute
```
mvn exec:java
```
</details>

<details>
<summary>Endpoints</summary><br>

- `GET /health` - Checks the health satus of the API
- `POST /sellers` - Allows sellers to create an account and list their products.
- `GET /sellers` - Retrieves a list of top sellers based on a ranking criteria.
- `GET /sellers/{sellerId}` - Retrieves information about a specific seller.
- `POST /products` - Allows sellers to create a new product listing.
- `GET /products` - Retrieves a list of products with optional filters such as sellerId, title, category, minPrice and maxPrice
- `GET /products/{productId}` - Retrieves detailed information about a specific product.
- `POST /products/{productId}/sell` - Marks a product as sold by the seller
- `POST /products/{productId}/offers` - Allows buyers to make an offer on a product

</details>

<details>
<summary>How to contribute</summary><br>

Everyone is welcomed to contribute! If you are interested, see the [Contribution Guide](CONTRIBUTIONS.md).

</details>
