package uni.isw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.model.AdministradorEmpresa;
import uni.isw.repository.AdministradorEmpresaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorEmpresaService{
    @Autowired
    AdministradorEmpresaRepository administradorEmpresaRepository;
    public List<AdministradorEmpresa> getAdministradoresEmpresa(){
        return (List<AdministradorEmpresa>) administradorEmpresaRepository.findAll();
    }
    public Optional<AdministradorEmpresa> getAdministradorEmpresa(Long id){
        return administradorEmpresaRepository.findById(id);
    }
    public void saveOrUpdate(AdministradorEmpresa admin){
        administradorEmpresaRepository.save(admin);
    }
    public void delete(Long id){
        administradorEmpresaRepository.deleteById(id);
    }
}
