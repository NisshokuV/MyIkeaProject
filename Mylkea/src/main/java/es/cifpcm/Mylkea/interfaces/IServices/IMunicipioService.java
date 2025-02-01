package es.cifpcm.Mylkea.interfaces.IServices;

import es.cifpcm.Mylkea.models.Municipio;
import java.util.List;

public interface IMunicipioService
{
    public List<Municipio> getMunicipios();
    public Municipio getMunicipioById(Integer id);
}
