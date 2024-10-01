package fatec.sp.gov.br.anshinpet.domain.service;

import fatec.sp.gov.br.anshinpet.domain.model.Doacao;
import fatec.sp.gov.br.anshinpet.domain.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    public List<Doacao> listar(){
        return  doacaoRepository.findAll();
    }

    public List<Doacao> buscarPortipo(String tipoDoacao){
        return doacaoRepository.findByTipo(tipoDoacao);
    }
}
