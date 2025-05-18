package com.vg.productservice.service;

import com.vg.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.random.RandomGenerator;

@Service
public class ProductService {
    public static Map<Integer, Product> inMemoryDB = new HashMap<>();
//    @Autowired
//    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        int id = RandomGenerator.getDefault().nextInt(100,200);
        product.setId(id);
        inMemoryDB.put(id, product);
        if(inMemoryDB.size()>20){
            inMemoryDB = new HashMap<>();
        }
        return product;
//        return productRepository.save(product);
    }

    public Product updateProduct(Integer productId, Product updatedProduct) {
//        Product existingProduct = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
        Product existingProduct = inMemoryDB.get(productId);
        if(existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStock(updatedProduct.getStock());
//            return productRepository.save(existingProduct);
            inMemoryDB.put(existingProduct.getId(), existingProduct);
            return existingProduct;
        }
        return null;
    }

    public Product getProductById(Integer productId) {
//        return productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
        return inMemoryDB.get(productId);
    }

    public List<Product> getAllProducts() {
//        return productRepository.findAll();
        List<Product> ls = new ArrayList<>();
        for(Map.Entry<Integer,Product> entry : inMemoryDB.entrySet())
            ls.add(entry.getValue());
        return ls;
    }

    public void deleteProduct(Integer productId) {
//        productRepository.deleteById(productId);
        inMemoryDB.remove(productId);
    }
}

