import { useEffect, useState } from "react";
import { Row, Col } from "react-bootstrap";
import Button from "react-bootstrap/esm/Button";
import { BsFillTrashFill } from "react-icons/bs";

type Product = {
  id: number;
  name: string;
};

function ProductList() {
  const [products, setProducts] = useState<Product[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/product", {
      method: "GET",
      headers: {
        Accept: "application/json",
      },
    })
      .then((response) => response.json())
      .then((products) => setProducts(products))
      .catch((err) => console.log(err.message));
  }, []);

  function deleteProduct(productToDelete: Product) {
    fetch("http://localhost:8080/api/v1/product/" + productToDelete.id, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
      },
    })
      .then((response) => {
        if (response.status != 200) {
          console.log(
            "Failed to delete product " +
              productToDelete.id +
              ": " +
              response.body
          );
          return;
        }

        setProducts(
          products.filter((product) => product.name != productToDelete.name)
        );
      })
      .catch((err) => console.log(err.message));
  }

  return (
    <>
      <h1>Products</h1>
      <Row>
        <Col>New product</Col>
        <Col>
          <Button>+</Button>
        </Col>
      </Row>
      <ul className="list-group">
        {products.map((product) => (
          <li
            key={product.name}
            className="list-group-item"
            onClick={() => deleteProduct(product)}
          >
            <Row>
              <Col>{product.name}</Col>
              <Col>
                <Button variant="danger">
                  <BsFillTrashFill />
                </Button>
              </Col>
            </Row>
          </li>
        ))}
      </ul>
    </>
  );
}

export default ProductList;
