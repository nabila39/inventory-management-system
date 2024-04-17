package com.assi.inventoryApi.controller;
import com.assi.inventoryApi.model.dto.ProDto;
import com.assi.inventoryApi.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProController {

    @Autowired
    private ProService prdService;

    @GetMapping("/getProducts")
    public List<ProDto> getAll(){
        return prdService.getAll();
    }
    @GetMapping("/getProductById")
    public ProDto getById(@RequestParam Integer id) {
        return prdService.getById(id);
    }
    @PostMapping("/addProduct")
    public ProDto addProduct(@RequestBody ProDto proDto) {
        return prdService.addNewProduct(proDto);
    }

    /*@PostMapping("/updateProduct")
    public ProDto updateProduct(@RequestBody ProDto proDto) {
        return prdService.updateProduct(proDto);
    }*/
    @GetMapping("/deleteProductd")
    public void deletById(@RequestParam Integer id) {
         prdService.deleteById(id);
    }
    @PatchMapping("/updateProductPartially")
    public ProDto updateProductPartially(@RequestParam Integer id, @RequestBody Map<String, Object> updates) {
        return prdService.updateProductPartially(id, updates);
    }
    @PutMapping("/updateProductFully")
    public ProDto updateProductFully(@RequestParam Integer id, @RequestBody ProDto proDto) {
        return prdService.updateProductFully(id, proDto);
    }
}
