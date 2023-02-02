package br.faccat.projeto.projetofaccat.controller;

import br.faccat.projeto.projetofaccat.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController ()
@RequestMapping(value = "/files")
public class FilesController {

    private FileService fileService;

    public  FilesController(FileService fileService) {
        this.fileService=fileService;
    }

    @PostMapping(value = "/enviar")
    public ResponseEntity<Void> saveFile(@RequestBody MultipartFile file){

        fileService.saveFile(file, "pastanova");

        return ResponseEntity.ok().build();
    }

}
