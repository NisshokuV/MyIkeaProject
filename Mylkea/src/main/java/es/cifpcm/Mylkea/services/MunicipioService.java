package es.cifpcm.Mylkea.services;

import es.cifpcm.Mylkea.interfaces.IServices.IMunicipioService;
import es.cifpcm.Mylkea.interfaces.repository.MunicipioRepository;
import es.cifpcm.Mylkea.models.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService implements IMunicipioService
{
    @Autowired
    MunicipioRepository repo;

    @Override
    public Municipio getMunicipioById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));
    }

    @Override
    public List<Municipio> getMunicipios() {
        return repo.findAll();
    }
}
