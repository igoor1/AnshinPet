package fatec.sp.gov.br.anshinpet.api.controller;

import fatec.sp.gov.br.anshinpet.api.assembler.UsuarioFotoModelAssembler;
import fatec.sp.gov.br.anshinpet.api.dto.UsuarioFotoDTO;
import fatec.sp.gov.br.anshinpet.api.dto.input.FotoInput;
import fatec.sp.gov.br.anshinpet.domain.exception.EntidadeNaoEncontradaException;
import fatec.sp.gov.br.anshinpet.domain.model.Usuario;
import fatec.sp.gov.br.anshinpet.domain.model.UsuarioFoto;
import fatec.sp.gov.br.anshinpet.domain.service.FotoStorageService;
import fatec.sp.gov.br.anshinpet.domain.service.UsuarioFotoService;
import fatec.sp.gov.br.anshinpet.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios/{usuarioId}/foto")
public class UsuarioFotoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioFotoService usuarioFotoService;

    @Autowired
    private UsuarioFotoModelAssembler usuarioFotoAssembler;

    @Autowired
    private FotoStorageService fotoStorage;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioFotoDTO buscar(@PathVariable Long usuarioId){
        UsuarioFoto usuarioFoto = usuarioFotoService.buscarOuFalhar(usuarioId);
        return usuarioFotoAssembler.toModel(usuarioFoto);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> servir(@PathVariable Long usuarioId,
                                                      @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {

        try {
            UsuarioFoto usuarioFoto = usuarioFotoService.buscarOuFalhar(usuarioId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(usuarioFoto.getContentType());
            List<MediaType> mediaTypeAceitas = MediaType.parseMediaTypes(acceptHeader);
            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypeAceitas);

            FotoStorageService.FotoRecuperada fotoRecuperada = fotoStorage.recuperar(usuarioFoto.getNomeArquivo());

            if (fotoRecuperada.temUrl()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header(HttpHeaders.LOCATION, fotoRecuperada.getUrl()).build();
            } else{
                return ResponseEntity.ok().contentType(mediaTypeFoto).body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UsuarioFotoDTO atualizarFoto(@PathVariable Long usuarioId, @Valid FotoInput fotoInput) throws IOException {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        MultipartFile arquivo = fotoInput.getArquivo();

        UsuarioFoto foto = new UsuarioFoto();
        foto.setUsuario(usuario);
        foto.setDescricao(fotoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        UsuarioFoto fotoSalva = usuarioFotoService.salvar(foto, arquivo.getInputStream());

        return usuarioFotoAssembler.toModel(fotoSalva);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId){
        usuarioFotoService.excluir(usuarioId);
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
