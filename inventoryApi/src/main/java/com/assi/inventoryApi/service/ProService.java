package com.assi.inventoryApi.service;
import com.assi.inventoryApi.model.dto.ProDto;
import com.assi.inventoryApi.model.entity.Product;
import com.assi.inventoryApi.repository.ProRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProService {
    @Autowired
    private ProRepo prdRepo;

    public List<ProDto> getAll() {
        List<Product> products = prdRepo.findAll();
        return products.stream()
                .map(ProDto::toDto)
                .collect(Collectors.toList());
    }

    public ProDto getById(Integer id) {
        Optional<Product> productOptional = prdRepo.findById(id);
        return productOptional.map(ProDto::toDto).orElse(null);
    }

    public ProDto addNewProduct(@RequestBody ProDto pro) {

        return ProDto.toDto(prdRepo.save(Product.toEntity(pro)));
    }

  /*  public ProDto updateProduct(@RequestBody ProDto pro) {
        return ProDto.toDto(prdRepo.save(Product.toEntity(pro)));
    }*/
    public void deleteById(Integer id) {
        this.prdRepo.deleteById(id);

    }
    public ProDto updateProductPartially(Integer id, Map<String, Object> updates) {
        Optional<Product> optionalProduct = prdRepo.findById(id);
        if (optionalProduct.isPresent()) {//true
            Product product = optionalProduct.get();
            if (updates.containsKey("pname")) {
                product.setPName((String) updates.get("pname"));
            }
            if (updates.containsKey("price")) {
                Float price = null;
                try {
                    price = Float.parseFloat(updates.get("price").toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                product.setPrice(price);
            }
            Product updatedProduct = prdRepo.save(product);
            return ProDto.toDto(updatedProduct);
        }
        return null;
    }
    public ProDto updateProductFully(Integer id, ProDto proDto) {
        Optional<Product> optionalProduct = prdRepo.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setPName(proDto.getPName());
            product.setPrice(proDto.getPrice());
            Product updatedProduct = prdRepo.save(product);
            return ProDto.toDto(updatedProduct);
        }
        return null;
    }

}
