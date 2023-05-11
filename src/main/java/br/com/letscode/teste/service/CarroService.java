package br.com.letscode.teste.service;

import br.com.letscode.teste.model.Carro;
import br.com.letscode.teste.model.Classe;
import br.com.letscode.teste.repository.CarroRepository;
import br.com.letscode.teste.service.exception.ClasseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarroService {
    @Autowired
    CarroRepository carroRepository;

    @Autowired
    ClasseService classeService;

    public List<Carro> listar() {
        return carroRepository.findWithClasses();
    }

    public void criar(Carro carro) {
        carro.setClasse(
                classeService.getByUid(carro.getClasse().getUid())
        );
        carro.setUid(UUID.randomUUID().toString());
        carroRepository.saveAndFlush(carro);
    }

    public void editar(String uid, Carro carro) throws ClasseNotFoundException {
        List<Carro> carros = carroRepository.findByUid(uid);
        if (carros.size() == 1) {
            Carro carroDB = carros.get(0);
            carroDB.setNome(carro.getNome());
            carroDB.setPlaca(carro.getPlaca());
            Classe classe = classeService.getByUid(carro.getClasse().getUid());
            carroDB.setClasse(classe);
            carroRepository.saveAndFlush(carroDB);
        } else {
            throw new ClasseNotFoundException("O carro "+ uid +" não foi encontrado.");
        }
    }

    public boolean deletar(String uid) {
        List<Carro> carros = carroRepository.findByUid(uid);
        if (carros.size() == 0) {
            return false;
        }
        Carro carro = carros.get(0);
        carroRepository.delete(carro);
        return true;
    }

    public Carro getByUid(String uid) {
        List<Carro> carros = carroRepository.findByUid(uid);
        if (carros.size() == 1) {
            return carros.get(0);
        }
        return null;
    }
}
