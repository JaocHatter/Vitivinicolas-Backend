package com.example.demo.controller;

import com.example.demo.model.AdministradorEmpresa;
import com.example.demo.service.AdministradorEmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/administradorempresa")
public class AdministradorEmpresaController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdministradorEmpresaService adminService;

    //GET
    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdministradorEmpresa>> getAdministradorEmpresa() {
        logger.info(">listar");

        List<AdministradorEmpresa>  listaAdmins = null;
        try {
            listaAdmins = adminService.getAdministradoresEmpresa();
        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(">listar");

        return new ResponseEntity<>(listaAdmins, HttpStatus.OK);
    }
    //GET BY ID
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministradorEmpresa> getAdministradorEmpresa(@RequestBody Optional<AdministradorEmpresa> admin) {
        logger.info(">search" +  admin.toString());
        try {
            admin = adminService.getAdministradorEmpresa(admin.get().getIdAdministradorEmpresa());

        } catch (Exception e) {
            logger.error("Unexpected Exception caught.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(">search" +  admin.toString());
        return new ResponseEntity<>(admin.get(), HttpStatus.OK);
    }

    //CREATE
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministradorEmpresa> agregar(@RequestBody AdministradorEmpresa admin){
        logger.info(">agregar: " + admin.toString());
        try{
            adminService.saveOrUpdate(admin);
        } catch(Exception e){
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(">agregar: " + admin.toString());
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    //UPDATE
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministradorEmpresa> actualizar(@RequestBody AdministradorEmpresa admin){

        logger.info(">actualizar: " + admin.toString());
        try{
            adminService.saveOrUpdate(admin);
        } catch(Exception e){
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(">actualizar: " + admin.toString());
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    //DELETE
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministradorEmpresa> delete(@RequestBody Optional<AdministradorEmpresa> admin){

        logger.info(">eliminar: " + admin.toString() );
        try{
            admin = adminService.getAdministradorEmpresa(admin.get().getIdAdministradorEmpresa());
            if (admin.isPresent()) //eliminar si y solo si está presente
                adminService.delete(admin.get().getIdAdministradorEmpresa());
        } catch(Exception e){
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info(">eliminar: " + admin.toString() );
        return new ResponseEntity<>(admin.get(), HttpStatus.OK);
    }
}
