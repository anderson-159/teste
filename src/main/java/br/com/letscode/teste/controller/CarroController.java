package br.com.letscode.teste.controller;

import br.com.letscode.teste.dto.CarroDTO;
import br.com.letscode.teste.dto.FactoryDTO;
import br.com.letscode.teste.dto.ResultadoDTO;
import br.com.letscode.teste.service.CarroService;
import br.com.letscode.teste.service.ClasseService;
import br.com.letscode.teste.service.exception.ClasseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController extends BaseController {
    @Autowired
    private CarroService carroService;
    @Autowired
    private ClasseService classeService;
    @GetMapping("/lista")
    public List<CarroDTO> listar(@RequestParam String filtro) {
        if (filtro != null && !filtro.isEmpty()) {
            //return carroService.filterByNome(filtro);
        }
        return FactoryDTO.carrosToDTO(carroService.listar());
    }
    @GetMapping
    public CarroDTO detalhar(@RequestParam String nome) { return CarroDTO.builder().nome(nome).placa(150).build(); }

    @PostMapping
    public ResponseEntity<ResultadoDTO> criar(@RequestBody @Valid CarroDTO carroDTO) {
       /* if (!classeService.contains(carroDTO.getClasse().getNome())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResultadoDTO()
                            .putError("classe", "Não existe.")
            );
        } */
        System.out.println("Nome do Carro: "+ carroDTO.getNome());
        carroService.criar(FactoryDTO.dtoToEntity(carroDTO));
        return ResponseEntity.ok(
                new ResultadoDTO()
                        .setResultado(true)
                        .setMensagem("Carro "+ carroDTO.getNome() +" criado com sucesso..." )
        );
    }
    @PutMapping
    public ResponseEntity<ResultadoDTO> editar(
            @RequestParam(name = "uid") String uid,
            @RequestBody @Valid CarroDTO carroDTO
    ) {
        try {
            carroService.editar(uid, FactoryDTO.dtoToEntity(carroDTO));
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
                        .setMensagem("O carro "+ uid +" foi editada com sucesso")
        );
    }

    @DeleteMapping
    public ResponseEntity<ResultadoDTO> deletar(
            @RequestParam(name = "uid") String uid
    ) {
        boolean resultado = carroService.deletar(uid);
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
                        .setMensagem("O uid do carro "+ uid +" foi removida com sucesso")
        );
    }
}

