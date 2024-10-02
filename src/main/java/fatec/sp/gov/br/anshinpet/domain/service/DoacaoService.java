package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.DoacaoNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.exception.NegocioException;
import fatec.sp.gov.br.anshinpet.domain.model.Doacao;
import fatec.sp.gov.br.anshinpet.domain.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    public List<Doacao> listar(){
        return  doacaoRepository.findAll();
    }

    public List<Doacao> buscarPorTipo(String tipoDoacao){
        return doacaoRepository.findByTipo(tipoDoacao);
    }

    @Transactional
    public Doacao salvar(Doacao doacao){
        return doacaoRepository.save(doacao);
    }

    @Transactional
    public void excluir(Long doacaoId){
        try{
            doacaoRepository.deleteById(doacaoId);

        } catch (EmptyResultDataAccessException e){
            throw new DoacaoNaoEncontradaException(doacaoId);

        } catch (DataIntegrityViolationException e){
            throw new NegocioException("Doação não pode ser removido, pois está em uso");
        }
    }

    public Doacao buscarOuFalhar(Long doacaoId){
        return doacaoRepository.findById(doacaoId)
                .orElseThrow(()-> new DoacaoNaoEncontradaException(doacaoId));
    }

    public List<Doacao> buscarPortipo(String tipoDoacao){
        return doacaoRepository.findByTipo(tipoDoacao);
    }
}
