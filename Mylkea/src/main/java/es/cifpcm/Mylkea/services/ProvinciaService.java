package es.cifpcm.Mylkea.services;

import es.cifpcm.Mylkea.interfaces.IServices.IProvinciaService;
import es.cifpcm.Mylkea.interfaces.repository.ProvinciaRepository;
import es.cifpcm.Mylkea.models.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService implements IProvinciaService
{
    @Autowired
    ProvinciaRepository repo;

    @Override
    public Provincia getProvinciaById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Provincia no encontrada"));
    }

    @Override
    public List<Provincia> getProvincias() {
        return repo.findAll();
    }
}
