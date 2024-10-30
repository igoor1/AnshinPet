package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.DoacaoNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.exception.InteresseNaoEncontradoException;
import fatec.sp.gov.br.anshinpet.domain.model.InteresseAdocao;
import fatec.sp.gov.br.anshinpet.domain.repository.InteresseAdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresseAdocaoService {

    @Autowired
    private InteresseAdocaoRepository interesseAdocaoRepository;

    public List<InteresseAdocao> listar(){
        return interesseAdocaoRepository.findAll();
    }

    public InteresseAdocao salvar(InteresseAdocao interesseAdocao){
        return interesseAdocaoRepository.save(interesseAdocao);
    }

    public void excluir(Long interesseId){
        interesseAdocaoRepository.deleteById(interesseId);
    }

    public InteresseAdocao buscarOuFalhar(Long interessedId){
        return interesseAdocaoRepository.findById(interessedId)
                .orElseThrow(()-> new InteresseNaoEncontradoException(interessedId));
    }

    public List<InteresseAdocao> buscarPorNome(String nome){
        return interesseAdocaoRepository.findByNameContaining(nome);
    }
}
