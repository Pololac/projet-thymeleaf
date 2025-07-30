🛒 Thymeleaf Cart Project

This starter project demonstrates a simple shopping cart functionality using Spring Boot and Thymeleaf, including session management and database interaction.

Features

Fixtures: Easily seed database with sample users and products using CommandLineRunner.

Product Listing: Display paginated products with 'Add to Cart' functionality.

Cart Service:

Add and remove products from the cart.

Calculate the total price.

Checkout to clear the cart and update product stock.

Session-based cart persistence.

Cart Management Page (/cart):

View products added to the cart.

Manage cart content and see the total price.

Bonus Features

Manage product quantities in the cart (multiple quantities per product).

Persistent cart for logged-in users, maintaining cart content across sessions.

Technologies Used

Spring Boot

Thymeleaf

Spring Data JPA

H2 Database (for development)

Getting Started

Clone the repository, run the application with Maven, and navigate to http://localhost:8080 to access the app.

git clone https://github.com/Pololac/projet-thymeleaf.git
cd projet-thymeleaf
./mvnw spring-boot:run

Contributing

Feel free to contribute enhancements, bug fixes, or additional features by creating pull requests.

