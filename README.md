# Online Store
The purpose of this project was to create a simple, yet fully functional online electronics store. Additionally, one of the sub-goals of the project was to test and evaluate as many technologies as possible.

## Tech stack
* Kubernetes
* MongoDB
* Various web frameworks (Spring Boot, Gin, Express.js, etc.)
* Service mesh (Istio)

## API design
| API                | Base path        | Description                                                       | Language   | Framework     |
|--------------------|------------------|-------------------------------------------------------------------|------------|---------------|
| Catalog API        | /products        | Create, edit and manage product catalog. Extract product details. | Java       | Spring Boot   |
| Orders API         | /orders          | Manage information about orders.                                  | Go         | Gin           |
| Inventory API      | /inventory       | Manage information about available inventory.                     | Rust       | Rocket        |
| Recommendation API | /recommendations | Recommend users new products.                                     | JavaScript | Express.js    |
| Comments API       | /comments        | Store user comments.                                              | Scala      | Play          |
| Support API        | /support         | Manage support tickets.                                           | Ruby       | Ruby on Rails |
| Authentication API | /auth            | XXX                                                               | XXX        | XXX           |
| Analytics API      | /analytics       | XXX                                                               | XXX        | XXX           |
| Payment API        | /payment         | XXX                                                               | XXX        | XXX           |
| Promotion API      | /promotion       | XXX                                                               | XXX        | XXX           |

## TODOs
Spring HATEOAS