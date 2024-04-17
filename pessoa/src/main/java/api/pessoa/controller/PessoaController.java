package api.pessoa.controller;

import api.pessoa.model.PessoaModel;
import api.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaModel>> listar(){
        List<PessoaModel> pessoaModels = pessoaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModels);
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<Object> buscar(@PathVariable(value = "idPessoa") Long idPessoa){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(idPessoa);
        if (!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa nao encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional);
    }

    @PostMapping
    public ResponseEntity<PessoaModel> adicionar(@RequestBody PessoaModel pessoaModel){
        PessoaModel pessoaModel1 = pessoaService.save(pessoaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaModel1);
    }

    @PutMapping("/{idPessoa}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "idPessoa") Long pessoaId, @RequestBody PessoaModel pessoaModel) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(pessoaId);

        if (!pessoaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa nao encontrada!");
        } else {
            var pessoaModel1 = pessoaModelOptional.get();
            pessoaModel1.setNome(pessoaModel.getNome());
            pessoaModel1.setCpf(pessoaModel.getCpf());
            pessoaModel1.setEmail(pessoaModel.getEmail());
            pessoaModel1.setTelefone(pessoaModel.getTelefone());
            pessoaModel1.setEndereco(pessoaModel.getEndereco());
            return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoaModel1));
        }
    }

    @DeleteMapping("/{idPessoa}")
    public ResponseEntity<Object> apagar(@PathVariable(value = "idPessoa") Long pessoaId){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(pessoaId);
        if (!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa nao encontrada");
        }
        pessoaService.delete(pessoaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada");
    }

}
