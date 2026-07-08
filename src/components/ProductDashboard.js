// import { useEffect, useState } from "react";
// import ProductService from "../services/ProductService";

// function ProductDashboard() {

//     const [products, setProducts] = useState([]);

//     useEffect(() => {
//         loadProducts();
//     }, []);

//     const loadProducts = () => {
//         ProductService.getAllProducts()
//             .then((res) => {
//                 setProducts(res.data);
//             });
//     };

//     return (
//         <div>
//             <h2>Product List</h2>

//             <table border="1">
//                 <thead>
//                     <tr>
//                         <th>ID</th>
//                         <th>Name</th>
//                         <th>Price</th>
//                     </tr>
//                 </thead>

//                 <tbody>
//                     {products.map((p) => (
//                         <tr key={p.id}>
//                             <td>{p.id}</td>
//                             <td>{p.name}</td>
//                             <td>{p.price}</td>
//                         </tr>
//                     ))}
//                 </tbody>

//             </table>
//         </div>
//     );
// }

// export default ProductDashboard;