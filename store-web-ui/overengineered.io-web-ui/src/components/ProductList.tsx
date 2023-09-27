import { useState, useEffect } from "react";

function ProductList() {
  const [products, setProducts] = useState<string[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/product", {
      method: "GET",
      headers: {
        Accept: "application/json",
      },
    })
      .then((response) => response.json())
      .then((json) => json.map((product: { name: string }) => product.name))
      .then((products) => setProducts(products))
      .catch((err) => console.log(err.message));
  }, []);

  return (
    <>
      <ul className="list-group">
        {products.map((product) => (
          <li key={product} className="list-group-item">
            {product}
          </li>
        ))}
      </ul>
    </>
  );
}

export default ProductList;
