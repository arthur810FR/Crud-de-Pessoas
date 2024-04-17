package api.pessoa.service;

import api.pessoa.model.PessoaModel;

import java.util.List;
import java.util.Optional;

public interface PessoaService {
    List<PessoaModel> findAll();

    PessoaModel save(PessoaModel pessoaModel);

    Optional<PessoaModel> findById(Long idPessoa);

    void delete(PessoaModel pessoaModel);
}
