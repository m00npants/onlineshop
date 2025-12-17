```mermaid
classDiagram

    class Customer {
        -id:int
        -name:String
        -email:String
        -orderHistory: List~Order~
        +addOrder(order:Order)
        +getOrderHistory(): List~Order~
    }

    class Product {
        -id:int
        -name:String
        -price:double
        -category:String
    }

    class Order {
        -id:int
        -customer:Customer
        -products: List~Product~
        +addProduct(product:Product)
        +removeProduct(productId:int): boolean
        +getTotalPrice():double
        +printSummary()
    }

    class ProductCatalog {
        -products: List~Product~
        +addProduct(product:Product)
        +getById(id:int):Product
        +getAll():List~Product~
        +printAll()
    }

    class OrderService {
        -nextOrderId:int
        +createOrder(customer:Customer, products:List~Product~):Order
    }

%% Relationships
    Customer "1" --> "0..*" Order : has
    Order "1" --> "1..*" Product : contains
    ProductCatalog "1" --> "0..*" Product : stores
    OrderService "1" --> "0..*" Order : creates

```


Consolbased onlineshop contains the following classes:
Customer, Product, Order and App

✅ Customer
- Fields: id, name, email
- No order history
- No relationship to products
- Only stored in a list inside App
- 
- 
  ✅ Product
- Fields: id, name, price
- No category
- No catalog
- Created manually each time
- 
- 
  ✅ Order
- Fields: id, customer, list of products
- No date
- No status
- No connection back to the customer
- Only one order existed at a time
- 
- 
  ✅ App
- Did everything:
- Created customers
- Created orders
- Created products
- Printed results
- Handled user input

gonna try add the optional task to 
- Add one or more classes **or** new fields/methods
- Add relationships that make sense in the scenario
- Explain your design choices:
    - What you added
    - Why you added it
    - How it improves the system


- Old limitations:
    - No Menu
    - No loops
    - no Catalog
    - it was hard coded


- New features:
    - uses a ProductCatalog class to manage products
    - uses an OrderService class to handle order creation and management
    - Uses loops and a menu with options to view products, create orders, and view order history
    - clenaner and more modular design
    - easier to read , maintain and expand.
    - you can now remove products from orders
    - Added an alternative to start the app with UI
    - added so you can add a new customer
    - added so you can delete a customer
    


