SHOPPING CART FULL-STACK PROJECT
Vue 3 + Spring Boot + MongoDB

REQUIREMENTS
- Java 17+
- Maven
- Node.js and npm
- MongoDB running locally on port 27017

BACKEND
1. Start MongoDB.
2. Open a terminal in the backend folder.
3. Run: mvn spring-boot:run
   API base URL: http://localhost:8080/api/products
4. Optional: test GET http://localhost:8080/api/products in a browser or Postman.

FRONTEND
1. Open another terminal in the frontend folder.
2. Run: npm install
3. Run: npm run dev
4. Open the URL printed by Vite, usually http://localhost:5173.

FEATURES
- View products from MongoDB
- Add products to cart
- Increase/decrease quantity
- Remove items and clear cart
- Calculate total items and total price
- Create a simple order (saved to MongoDB)
- List saved orders

MONGODB
URI: mongodb://localhost:27017/shoppingCartDB
Collections are created automatically when data is saved.

This is a student-friendly lab project. The cart is held in frontend memory; placed orders are saved to MongoDB.
