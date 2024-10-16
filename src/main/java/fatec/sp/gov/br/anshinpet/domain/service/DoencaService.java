package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.NegocioException;
import fatec.sp.gov.br.anshinpet.domain.exception.DoencaNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.model.Doenca;
import fatec.sp.gov.br.anshinpet.domain.repository.DoencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoencaService {

    @Autowired
    private DoencaRepository doencaRepository;

    public List<Doenca> listar(){
        return doencaRepository.findAll();
    }

    @Transactional
    public Doenca salvar(Doenca doenca){
        return doencaRepository.save(doenca);
    }


    @Transactional
    public void excluir (Long doencaId){
        try{
            doencaRepository.deleteById(doencaId);
        }catch (EmptyResultDataAccessException e){
            throw new DoencaNaoEncontradaException(doencaId);
        }catch (DataIntegrityViolationException e){
            throw new NegocioException(String.format("ue", doencaId));
        }
    }

    public Doenca buscarOuFalhar(Long doencaId){
        return doencaRepository.findById(doencaId)
                .orElseThrow(()-> new DoencaNaoEncontradaException(doencaId));
    }
}
