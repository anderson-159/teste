package br.com.letscode.teste.controller;


import br.com.letscode.teste.dto.ClasseDTO;
import br.com.letscode.teste.dto.FactoryDTO;
import br.com.letscode.teste.dto.ResultadoDTO;
import br.com.letscode.teste.service.ClasseService;
import br.com.letscode.teste.service.exception.ClasseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/classe")
public class ClasseController extends BaseController {

    private ClasseService classeService;
    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<ClasseDTO>> listar() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(FactoryDTO.classesToDTO(classeService.listar()));
    }
    @PostMapping
    public ResponseEntity<ResultadoDTO> criar(@RequestBody @Valid ClasseDTO classeDTO) {
        classeService.criar(FactoryDTO.dtoToEntity(classeDTO));
            return ResponseEntity.ok(
                    new ResultadoDTO()
                                    .setResultado(true)
                                    .setMensagem("A classe do carro foi criada com sucesso.")
                    );
        }

    @PutMapping
    public ResponseEntity<ResultadoDTO> editar(
            @RequestParam(name = "uid") String uid,
            @RequestBody @Valid ClasseDTO classeDTO
    ) {
        try {
            classeService.editar(uid, FactoryDTO.dtoToEntity(classeDTO));
        } catch (ClasseNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem(e.getMessage())
                    );
        }
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("A classe de carro "+ uid +" foi editada com sucesso")
        );
    }
    @DeleteMapping
    public ResponseEntity<ResultadoDTO> deletar(
            @RequestParam(name = "uid") String uid
    ) {
        boolean resultado = classeService.deletar(uid);
        if (resultado == false) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            new ResultadoDTO()
                                    .setResultado(false)
                                    .setMensagem("A classe "+ uid +" nao foi encontrada.")
                    );
            }
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("A classe do carro "+ uid +" foi removida com sucesso")
        );
    }
}


