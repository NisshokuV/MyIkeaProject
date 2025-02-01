package es.cifpcm.Mylkea.interfaces.IServices;

import es.cifpcm.Mylkea.models.Provincia;
import java.util.List;

public interface IProvinciaService
{
    public List<Provincia> getProvincias();
    public Provincia getProvinciaById(Integer id);
}
