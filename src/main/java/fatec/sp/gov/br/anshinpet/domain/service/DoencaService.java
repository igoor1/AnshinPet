package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.NegocioException;
import fatec.sp.gov.br.anshinpet.domain.exception.doenca.DoencaNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.model.Doenca;
import fatec.sp.gov.br.anshinpet.domain.repository.DoencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DoencaService {

    @Autowired
    private DoencaRepository doencaRepository;

    public Doenca salvar(Doenca doenca){
        return doencaRepository.save(doenca);
    }

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
