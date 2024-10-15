package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.AnimalFotoModelAssembler;
import fatec.sp.gov.br.anshinpet.api.dto.AnimalFotoDTO;
import fatec.sp.gov.br.anshinpet.api.dto.input.AnimalFotoInput;
import fatec.sp.gov.br.anshinpet.domain.exception.EntidadeNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.model.Animal;
import fatec.sp.gov.br.anshinpet.domain.model.AnimalFoto;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalFotoService;
import fatec.sp.gov.br.anshinpet.domain.service.AnimalService;
import fatec.sp.gov.br.anshinpet.domain.service.FotoStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/animais/{animalId}/foto")
public class AnimalFotoController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalFotoService animalFotoService;

    @Autowired
    private AnimalFotoModelAssembler animalFotoAssembler;

    @Autowired
    private FotoStorageService fotoStorage;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public AnimalFotoDTO buscar(@PathVariable Long animalId){
        AnimalFoto animalFoto = animalFotoService.buscarOuFalhar(animalId);
        return animalFotoAssembler.toModel(animalFoto);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> servir(@PathVariable Long animalId,
            @RequestHeader(name = "Accept") String acceptHeader ) throws  HttpMediaTypeNotAcceptableException{

        try {
            AnimalFoto animalFoto = animalFotoService.buscarOuFalhar(animalId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(animalFoto.getContentType());
            List<MediaType> mediaTypeAceitas = MediaType.parseMediaTypes(acceptHeader);
            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypeAceitas);

            InputStream inputStream = fotoStorage.recuperar(animalFoto.getNomeArquivo());
            return ResponseEntity.ok().contentType(mediaTypeFoto)
                    .body(new InputStreamResource(inputStream));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AnimalFotoDTO atualizarFoto(@PathVariable Long animalId, @Valid AnimalFotoInput animalFotoInput) throws IOException {
        Animal animal = animalService.buscarOuFalhar(animalId);

        MultipartFile arquivo = animalFotoInput.getArquivo();

        AnimalFoto foto = new AnimalFoto();
        foto.setAnimal(animal);
        foto.setDescricao(animalFotoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        AnimalFoto fotoSalva = animalFotoService.salvar(foto, arquivo.getInputStream());

        return animalFotoAssembler.toModel(fotoSalva);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long animalId){
        animalFotoService.excluir(animalId);
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto,
                                                   List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {

        boolean compativel = mediaTypesAceitas.stream()
                .anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
        }
    }
}
