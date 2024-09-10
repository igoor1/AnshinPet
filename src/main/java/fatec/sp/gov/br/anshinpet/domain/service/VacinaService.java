package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.exception.VacinaNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.model.Vacina;
import fatec.sp.gov.br.anshinpet.domain.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;

    public Vacina salvar(Vacina vacina){
        return vacinaRepository.save(vacina);
    }

    public void excluir(Long vacinaId){
        try{
            vacinaRepository.deleteById(vacinaId);
        } catch (EmptyResultDataAccessException e){
            throw new VacinaNaoEncontradaException(vacinaId);
        }
    }

    public Vacina buscarOuFalhar(Long vacinaId){
        return vacinaRepository.findById(vacinaId)
                .orElseThrow(()-> new VacinaNaoEncontradaException(vacinaId));
    }
}
