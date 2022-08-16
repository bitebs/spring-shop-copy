package org.itstep.musicshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    ShopRepository shopRepository;

    public List<Artist> findAll(){
        return shopRepository.findAll();
    }

    public Optional<Artist> findById(Long id){
        return shopRepository.findById(id);
    }

    public Artist save(Artist artist){return shopRepository.save(artist);}

    public void deleteById(Long id){
        shopRepository.deleteById(id);
    }
}
