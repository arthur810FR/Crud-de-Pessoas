package api.pessoa.service.impl;

import api.pessoa.model.PessoaModel;
import api.pessoa.repository.PessoaRepository;
import api.pessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<PessoaModel> findAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public PessoaModel save(PessoaModel pessoaModel) {
        return pessoaRepository.save(pessoaModel);
    }

    @Override
    public Optional<PessoaModel> findById(Long idPessoa) {
        return pessoaRepository.findById(idPessoa);
    }

    @Override
    public void delete(PessoaModel pessoaModel) {
        pessoaRepository.delete(pessoaModel);
    }
}
