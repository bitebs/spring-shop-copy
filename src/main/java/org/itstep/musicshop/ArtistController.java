package org.itstep.musicshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArtistController {
    @Autowired
    ArtistService ArtistService;

    @GetMapping(value="/Artist")
    public List<Artist> findAll(){
        return ArtistService.findAll();
    }

    @GetMapping(value="/Artist/{id}")
    public Optional<Artist> findById(@PathVariable Long id){
        return ArtistService.findById(id);
    }

    @PostMapping(value="/Artist")
    Artist createOrSave(@RequestBody Artist artist) {
        return
                ArtistService.save
                        (artist);
    }

    @PutMapping(value="/Artist/{id}")
    Artist update(@RequestBody Artist newArtist, @PathVariable Long id) {
        return ArtistService.findById(id).map(Artist -> {
            Artist.setName(newArtist.getName());
            /*Artist.setColor(newJewelry.getColor());
            jewelry.setPrice(newJewelry.getPrice());
            jewelry.setPriceNew(newJewelry.getPriceNew());*/
            return
                    ArtistService.save
                            (Artist);
        }).orElse(null);
    }

    @DeleteMapping(value="/Artist/{id}")
    void deleteById(@PathVariable Long id) {
        ArtistService.deleteById(id);
        System.out.println("delete");
    }
}
