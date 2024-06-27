package com.example.demo.service;

import com.example.demo.model.AdministradorEmpresa;
import com.example.demo.repository.AdministradorEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public AdministradorEmpresa saveOrUpdate(AdministradorEmpresa admin){
        return administradorEmpresaRepository.save(admin);
    }
    public void delete(Long id){
        administradorEmpresaRepository.deleteById(id);
    }
}
