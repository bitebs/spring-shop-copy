package org.itstep.springshop;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("api")
public class JewelryController {
    @Autowired
    JewelryService jewelryService;

    @GetMapping(value="/jewelries")
    public List<Jewelry> findAll(){
        return jewelryService.findAll();
    }

    @GetMapping(value="/jewelries/{id}")
    public Optional<Jewelry> findById(@PathVariable Long id){
        return jewelryService.findById(id);
    }

    @PostMapping(value="/jewelries")
    Jewelry createOrSave(@RequestBody JewelryBase64 jewelry) {
        String base64Image = jewelry.getImage().split(",")[1];
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
        Jewelry newJewelery = new Jewelry();
        return
                jewelryService.save
                        (new Jewelry(newJewelery.getId(), jewelry.getName(), jewelry.getColor(),
                                jewelry.getPrice(), jewelry.getPriceNew(), imageBytes, new Material(1l, "name")));
    }

    @PutMapping(value="/jewelries/{id}")
    Jewelry updateUser(@RequestBody JewelryBase64 newJewelry, @PathVariable Long id) {
        return jewelryService.findById(id).map(jewelry -> {
            jewelry.setName(newJewelry.getName());
            jewelry.setColor(newJewelry.getColor());
            jewelry.setPrice(newJewelry.getPrice());
            jewelry.setPriceNew(newJewelry.getPriceNew());
            String base64Image = newJewelry.getImage().split(",")[1];
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
            jewelry.setImage(imageBytes);
            return
                    jewelryService.save
                            (jewelry);
        }).orElse(null);
    }

    @DeleteMapping(value="/jewelries/{id}")
    void deleteById(@PathVariable Long id) {
        jewelryService.deleteById(id);
        System.out.println("delete");
    }
}
