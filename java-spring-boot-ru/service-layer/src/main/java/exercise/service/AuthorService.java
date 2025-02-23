package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorMapper authorMapper;
    // BEGIN
    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO get(Long id) {
        var maybeAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        return authorMapper.map(maybeAuthor);
    }

    public AuthorDTO create(AuthorCreateDTO createDTO) {
        var model = authorMapper.map(createDTO);
        return authorMapper.map(authorRepository.save(model));
    }

    public AuthorDTO update(AuthorUpdateDTO updateDTO, Long id) {
        var mbModel = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        authorMapper.update(updateDTO, mbModel);
        return authorMapper.map(authorRepository.save(mbModel));
    }

    public void delete(Long id) {
        var mbModel = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        authorRepository.delete(mbModel);
    }
    // END
}
